package com.page_service.Interceptor;

import com.common.Entity.User;
import com.common.Util.SessionUtils;
import com.page_service.Cache.RegisterCache;
import com.page_service.Cache.WebSocketCache;
import com.page_service.service.UserService;
import com.page_service.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
@Component
public class RegisterInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private RegisterCache registerCache;
    @Autowired
    private UserService userService;

    /**
     * Controller执行前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = tokenUtils.getToken(request);

//        //判断是否存在token  //查看是否已过期
        if (StringUtils.isEmpty(token)||registerCache.Has(token)){
            //记下原来的后缀路径在preurl中,然后重定向到登录页面,就是域名了,
            // request.getRequestURL()完整路径,
            // request.getRequestURI()后缀路径,
            // tempContextUrl是域名,
            // response.sendRedirect()重定向
            //System.out.println("未登录，重定向到login");
//            response.sendRedirect("/mine/login");
//            return false;
        }else {
            User user = userService.getUserByUserId(Integer.valueOf(tokenUtils.getUserId(token)));
            SessionUtils.setLoginUserInSession(user);
        }

        //登录验证不使用token
        User loginUser = SessionUtils.getCurrentUser();
        if(loginUser==null){
            response.sendRedirect("/mine/login");
            return false;
        }
        return true;
    }

    /**
     * Controller执行后，视图未返回
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 所有请求处理完成，尚未返回客户端
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
