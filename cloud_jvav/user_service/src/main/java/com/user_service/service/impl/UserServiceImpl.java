package com.user_service.service.impl;

import com.user_service.mapper.UserMapper;
import com.common.Entity.User;
import com.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserId(Integer user_id) {
        return userMapper.queryById(user_id);
    }
    @Override
    public User getOtherByUserId(int user_id) {
        return userMapper.queryOtherById(user_id);
    }

    @Override
    public User getUserByPhone(String phone){
        return userMapper.queryByPhone(phone);
    }

    @Override
    public void setUserNameByUserId(String changeVal, Integer user_id) {
        userMapper.updateUserNameById(changeVal,user_id);
    }

    @Override
    public void setUserSexByUserId(Integer sex_type, Integer user_id) {
        userMapper.updateUserSexById(sex_type,user_id);
    }

    @Override
    public void setUserPassWordByUserId(String new_pwd, Integer user_id) {
        userMapper.updateUserPasswordById(new_pwd,user_id);
    }

    @Override
    public void setUserHeadById(Integer user_id, String fileName) {
        userMapper.updateUserHeadById(user_id,fileName);
    }

    @Override
    public Integer putUser(User user) {
        return userMapper.addUser(user);
    }
}
