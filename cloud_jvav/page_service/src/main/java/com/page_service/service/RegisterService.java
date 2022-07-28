package com.page_service.service;



import com.common.Entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface RegisterService {
    /**
     * 查询用户信息
     * @param phone
     * @return
     */
    public User getUserByPhone(String phone);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public boolean addUser(User user);

    /**
     * 用户登录
     * @param loginPhone
     * @param password
     * @param response
     * @return
     */
    public boolean login(String loginPhone, String password, HttpServletResponse response);

    List<User> getUserSession();
}
