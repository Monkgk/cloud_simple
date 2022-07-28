package com.common.Util;

import com.common.Entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

//HttpSession
public class SessionUtils {
    private static final String USER_IN_SESSION = "loginUser";

//    获得当前对象的session对象
    public static HttpSession   getSession(){
        return ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
    }

//      往Session中存放用户信息
    public static void setLoginUserInSession(User loginUser){
        if(loginUser    !=  null){
            getSession().setAttribute(USER_IN_SESSION,loginUser);
        }
    }

//    从Session中获取信息
    public static User getCurrentUser(){
        return (User)getSession().getAttribute(USER_IN_SESSION);
    }

//    设置Session失效
    public static   void    invalidSession(){
        getSession().invalidate();
    }
}
