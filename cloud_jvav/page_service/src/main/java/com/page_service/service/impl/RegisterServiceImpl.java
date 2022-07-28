package com.page_service.service.impl;


import com.common.Entity.User;
import com.common.Util.PasswordUtils;
import com.common.Util.SessionUtils;
import com.feign.clients.UserClient;
import com.page_service.Cache.RegisterCache;
import com.page_service.service.RegisterService;
import com.page_service.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;


/*
 * 用户登录和注册
 * */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    private UserClient  userClient;
    @Autowired
    private RegisterCache registerCache;

    @Override
    public User getUserByPhone(String phone){
        User user = userClient.getUserByPhone(phone);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addUser(User user) {
        //根据用户号码获取用户信息，用于判断用户是否存在
        User    user1   =   userClient.getUserByPhone(user.getUser_phone());
        //用户不存在则向数据库添加用户
        if (user1 != null){
            return false;
        }
        //密码加密
        user.setUser_pwd(PasswordUtils.generate(user.getUser_pwd()));
        if(user.getUser_name() == null || user.getUser_name() == ""){
            user.setUser_name("新用户");
        }
        if (user.getUser_head() == null || user.getUser_head() == ""){
            user.setUser_head("default_head");
        }
        if (userClient.addUser(user) <= 0){
            return false;
        }
        return true;//成功
    }

    @Override
    public boolean login(String loginPhone, String password, HttpServletResponse response) {
        User    user    =   userClient.getUserByPhone(loginPhone);
        if (user == null){
            return false;
        }
        //密码解密，与登录密码比对
        if(!PasswordUtils.verify(password,user.getUser_pwd())){
            return false;
        }
        //登录成功,存入session
        SessionUtils.setLoginUserInSession(user);
        //存入Redis,缓存记录存在该用户,缓存时间一天
        registerCache.put(user.getUser_id(),86400L);
        //生成token
        String token = tokenUtils.generateToken(user);
        Cookie cookie = new Cookie("token",token);
        //设置cookie的作用域：为”/“时，以在webapp文件夹下的所有应用共享cookie
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

    @Override
    public List<User> getUserSession(){
        return Arrays.asList(SessionUtils.getCurrentUser());
    }
}
