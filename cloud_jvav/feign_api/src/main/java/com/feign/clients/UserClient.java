package com.feign.clients;

import com.common.Entity.User;
import com.common.Vo.ResultVo;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@FeignClient("userservice")
public interface UserClient {

    /**
     * 根据用户Id获取用户简单信息
     * @param user_id
     * @return
     */
    @PostMapping("/user/otherInfo/{user_id}")
    User    getOtherByUserId(@PathVariable("user_id") int user_id);

    /**
     * 根据手机号获取用户信息
     * @param phone
     * @return
     */
    @PostMapping("/user/get/phone/{phone}")
    User    getUserByPhone(@PathVariable("phone") String phone);



    /**
     * 设置头像
     * @param user_id
     * @param file_name
     */
    @PostMapping("/user/set/head")
    void    setHeadByUserId(@RequestParam("user_id") Integer user_id,
                            @RequestParam("file_name") String file_name);

    @PostMapping("/user/set/info/{type}")
    ResultVo    setUserInfo(@RequestParam("user_id") int user_id,
                            @PathVariable("type")   String type,
                            @RequestBody   Map<String,Object> data);

    @PostMapping("/user/put")
    Integer addUser(@RequestBody User user);
}
