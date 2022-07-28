package com.page_service.service;

import com.common.Entity.User;

import java.io.IOException;

public interface UserService {
    /**
     * 根据user_id查找用户信息
     * @param user_id
     * @return
     */
    public User getUserByUserId(Integer user_id);

    /**
     * 获取用户头像
     */
    public byte[] getUserHeadByFileName(String file_name) throws IOException;

    /**
     * 根据user_id设置用户名
     * @param changeVal
     * @param user_id
     * @return
     */
    void setUserNameByUserId(String changeVal,Integer user_id);

    /**
     * 根据user_id设置用户性别
     * @param sex_type
     * @param user_id
     */
    void setUserSexByUserId(Integer sex_type,Integer user_id);

    /**
     * 根据user_id设置用户密码
     * @param new_pwd
     * @param user_id
     */
    void setUserPassWordByUserId(String new_pwd, Integer user_id);

    /**
     * 根据user_id设置头像信息
     * @param user_id
     * @param fileName 头像文件名
     */
    void setUserHeadById(Integer user_id, String fileName);
}
