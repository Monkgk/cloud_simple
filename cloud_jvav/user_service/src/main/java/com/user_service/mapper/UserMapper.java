package com.user_service.mapper;

import com.common.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper {
    //根据user_id查找用户信息
    @Select("select user_id,user_name,user_pwd,user_head,user_sex,user_phone from user where user_id=#{user_id}")
    User    queryById(Integer user_id);

    //根据user_name查找用户信息
    @Select("select user_id,user_name,user_pwd,user_head,user_sex,user_phone from user where user_name=#{user_name}")
    User    queryByName(String user_name);

    //根据user_id查找用户信息（无密码）
    @Select("select user_id,user_name,user_head,user_sex,user_phone from user where user_id=#{user_id}")
    User    queryOtherById(Integer user_id);

    //根据user_phone查找用户信息
    @Select("select user_id,user_name,user_pwd,user_head,user_sex,user_phone from user where user_phone=#{user_phone}")
    User    queryByPhone(String user_phone);

    //添加用户
    @Insert("INSERT into user(user_id,user_name,user_pwd,user_head,user_sex,user_phone) values(#{user_id},#{user_name},#{user_pwd},#{user_head},#{user_sex},#{user_phone})")
    Integer addUser(User user);

    //修改用户名
    @Update("update user set user_name=#{changeVal} where user_id=#{user_id}")
    void updateUserNameById(String changeVal, Integer user_id);

    //修改用户性别
    @Update("update user set user_sex=#{changeVal} where user_id=#{user_id}")
    void updateUserSexById(Integer changeVal, Integer user_id);

    //修改用户密码
    @Update("update user set user_pwd=#{new_pwd} where user_id=#{user_id}")
    void updateUserPasswordById(String new_pwd, Integer user_id);

    //修改用户头像
    @Update("update user set user_head=#{fileName} where user_id=#{user_id}")
    void updateUserHeadById(Integer user_id, String fileName);
}
