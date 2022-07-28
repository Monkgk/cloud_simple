package com.user_service.controller;

import com.common.Entity.User;
import com.common.Util.PasswordUtils;
import com.common.Vo.ResultCode;
import com.common.Vo.ResultVo;
import com.user_service.service.UserService;
import com.user_service.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    UserService userService;

    @RequestMapping("/get/phone/{phone}")
    public User getUserByPhone(@PathVariable("phone") String phone){
        return userService.getUserByPhone(phone);
    }

    @RequestMapping("/otherInfo/{user_id}")
    public User getOtherByUserId(@PathVariable("user_id") int user_id){
        return userService.getOtherByUserId(user_id);
    }

    @RequestMapping("/set/head")
    public void setUserHeadById(Integer user_id,String file_name){
        userService.setUserHeadById(user_id,file_name);
    }

    @RequestMapping("/set/info/{type}")
    public ResultVo setUserInfo(int user_id,
//            HttpServletRequest request,
                                @PathVariable("type") String type,
                                @RequestBody Map<String,Object> data){
        String changeVal = (String) data.get("value");
//        Integer user_id = Integer.valueOf(tokenUtils.getUserId(tokenUtils.getToken(request)));
        if(type.equals("name")&&!changeVal.isEmpty()&&changeVal!=""){
            //修改用户名
            userService.setUserNameByUserId(changeVal,user_id);
        }else if(type.equals("sex")){
            //修改性别
            //用户性别，1为男，0为女
            if (changeVal.equals("0")||changeVal.equals("1")){
                userService.setUserSexByUserId(Integer.valueOf(changeVal),user_id);
            }else if(changeVal.equals("")){
                userService.setUserSexByUserId(null,user_id);
            }else{
                return  ResultVo.returnFail(ResultCode.FAIL);
            }
        }else if(type.equals("pwd")){
            //修改密码
            String old_pwd = (String) data.get("oldPwd");
            String new_pwd = (String) data.get("newPwd");
            String user_pwd = userService.getUserByUserId(user_id).getUser_pwd();
            if(PasswordUtils.verify(old_pwd,user_pwd) && !new_pwd.isEmpty() && new_pwd!=""){
                //加盐加密
                String new_pwd_md5 = PasswordUtils.generate(new_pwd);
                //修改
                userService.setUserPassWordByUserId(new_pwd_md5,user_id);
                return ResultVo.success(ResultCode.SUCCESSS);
            }
            return ResultVo.returnFail(ResultCode.FAIL);
        }else{
            return ResultVo.returnFail(ResultCode.FAIL);
        }
        return ResultVo.success(ResultCode.SUCCESSS);
    }

    @RequestMapping("/put")
    public Integer putUser(@RequestBody User user){
        return userService.putUser(user);
    }
}
