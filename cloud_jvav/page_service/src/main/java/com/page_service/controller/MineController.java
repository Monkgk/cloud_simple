package com.page_service.controller;

import com.common.Entity.Order;
import com.common.Entity.User;
import com.common.Util.SessionUtils;
import com.common.Vo.ResultCode;
import com.common.Vo.ResultVo;
import com.common.exception.JvavException;
import com.page_service.service.ChangeService;
import com.page_service.service.OrderService;
import com.page_service.service.RegisterService;
import com.page_service.utils.TokenUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mine")
public class MineController {
    @Autowired
    OrderService    orderService;
    @Autowired
    ChangeService   changeService;
    @Autowired
    RegisterService registerService;
    @Autowired
    TokenUtils  tokenUtils;

    /**
     * 我的 页面
     * @return
     */
    @RequestMapping()
    public  String  Mine(){
        return "mine";
    }

    /**
     * 订单 页面
     * @param model
     * @param request
     * @return
     * @throws JvavException
     */
    @RequestMapping("order")
    public String   Order(Model model,HttpServletRequest request) throws JvavException {
        String token = tokenUtils.getToken(request);
        Integer userId = tokenUtils.getUserId(token);
        List<Order> orders = orderService.getOrderByUserId(userId);
        model.addAttribute("Orders",orders);
        return "order";
    }
//    优惠券 页面
    @RequestMapping("coupon")
    public String   Coupon(){
        return "coupon";
    }
//    vip 信息页面
    @RequestMapping("vip")
    public String Vip(){
        return "vip";
    }
    //登录 页面
    @RequestMapping("/login")
    public  String  login(){
        return "login";
    }
    //注册 页面
    @RequestMapping("/register")
    public String   register(){
        return "login_phone";
    }
    //海外手机登录界面
    @RequestMapping("oversea_phone")
    public String   oversea_phone(){
        return "oversea_phone";
    }


    //用户登录 请求
    @PostMapping("/tologin")
    public String   login(HttpServletRequest request,HttpServletResponse response,Model model){
        String  userPhone = request.getParameter("userPhone");
        String  passWord  = request.getParameter("userPwd");
        if(registerService.login(userPhone,passWord,response)){
            return "redirect:/";
        }
        model.addAttribute("fail","用户名/密码不正确");
        return "redirect:/mine/login";
    }

    //用户注册 请求
    @PostMapping("/toregister")
    @ResponseBody
    public ResultVo<Null> register(@RequestBody User user){
        return registerService.addUser(user) ? ResultVo.success(ResultCode.REGISTRY_SUCCESS):ResultVo.returnFail(ResultCode.REGISTRY_FAIL);
    }

    /**
     * 用户信息页面
     * @return
     */
    @RequestMapping("/info")
    public String info(Model model){
        User user = SessionUtils.getCurrentUser();
        model.addAttribute("user_info",user);
        return "info";
    }

    /**
     * 用户修改信息页面
     */
    @RequestMapping("/info/change/{type}")
    public String change(@PathVariable("type") String type,Model model){
        //放入用户信息
        model.addAttribute("user_info",SessionUtils.getCurrentUser());
        if (type.equals("head")){
            model.addAttribute("type","头像");
            model.addAttribute("url","head");
        }else if(type.equals("name")){
            model.addAttribute("type","用户名");
            model.addAttribute("url","name");
//        }else if(type.equals("phone")){
//            model.addAttribute("type","手机号码");
//            model.addAttribute("url","phone");
        }else if(type.equals("sex")){
            model.addAttribute("type","性别");
            model.addAttribute("url","sex");
        }else if(type.equals("setpwd")){
            model.addAttribute("type","密码");
            model.addAttribute("url","pwd");
        }else{
            return "info";
        }
        return "change_info";
    }

    @RequestMapping("/info/load/{type}")
    @ResponseBody
    public ResultVo toChangeHead(HttpServletRequest request,
                                 @PathVariable("type") String type,
                                 @RequestParam("file")MultipartFile file){
        return changeService.changeHead(type, file);
    }

    @RequestMapping("/info/set/{type}")
    @ResponseBody
    public ResultVo toChange(HttpServletRequest request,
                             @PathVariable("type") String type,
                             @RequestBody Map<String,Object> data){
        ResultVo    resultVo = changeService.changeInfo(request,type,data);
        return resultVo;
    }

}
