package com.message_service.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.common.Entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
public class TokenUtils {

    /**
     * 生成token
     * @param user
     * @return
     */
    public String generateToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create()
                .withAudience(String.valueOf(user.getUser_id()), user.getUser_name())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getUser_pwd()));
        return token;
    }


    /**
     *
     * @param token
//     * @param key
     * @return userId
     * 获取制定token中某个属性值
     */
    public String getUserId(String token) {
        List<String> list= JWT.decode(token).getAudience();
        String userId = list.get(0);
        return userId;
    }

    /**
     * 获取request
     * @return
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }


    /**
     *
     * @param request
     * @return
     * 获取token
     */
    public String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length>0) {
            for (Cookie cookie : cookies) {
                if (!cookie.getName().isEmpty() && cookie.getName().equals("token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}

