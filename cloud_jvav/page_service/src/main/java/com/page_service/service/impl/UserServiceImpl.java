package com.page_service.service.impl;

import com.common.Entity.User;
import com.feign.clients.UserClient;
import com.page_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    @Value("${file.save_path}")
    private String RESOURCE_SAVE_PATH;
    @Autowired
    UserClient  userClient;

    @Override
    public User getUserByUserId(Integer user_id) {
        return userClient.getOtherByUserId(user_id);
    }

    @Override
    public byte[] getUserHeadByFileName(String file_name) throws IOException {
        String file_path    =   RESOURCE_SAVE_PATH+file_name;
        File file = new File(file_path);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    @Override
    public void setUserNameByUserId(String changeVal, Integer user_id) {

    }

    @Override
    public void setUserSexByUserId(Integer sex_type, Integer user_id) {

    }

    @Override
    public void setUserPassWordByUserId(String new_pwd, Integer user_id) {

    }

    @Override
    public void setUserHeadById(Integer user_id, String fileName) {

    }
}
