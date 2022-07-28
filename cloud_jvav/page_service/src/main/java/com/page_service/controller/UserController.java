package com.page_service.controller;

import com.common.Util.SessionUtils;
import com.page_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/get/head/{file_name}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getUserHeadByName(@PathVariable("file_name") String file_name) throws IOException {
        return userService.getUserHeadByFileName(file_name);
    }

    /**
     * 清除cookie
     * @param request
     * @param response
     */
    @RequestMapping("/cookie/clear")
    @ResponseBody
    public void clearCookie(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie("token","");
        //设置cookie的作用域：为”/“时，以在webapp文件夹下的所有应用共享cookie
        cookie.setPath("/");
        response.addCookie(cookie);
        SessionUtils.invalidSession();
    }
}
