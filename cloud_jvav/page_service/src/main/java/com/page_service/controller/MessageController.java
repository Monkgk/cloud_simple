package com.page_service.controller;

import com.common.Entity.Message;
import com.page_service.service.MessageService;
import com.page_service.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    TokenUtils  tokenUtils;
    @Autowired
    MessageService  messageService;

    @RequestMapping()
    public String message(Model model){
        //获取消息类型列表
        List<Message> typeList= messageService.getTypeList();
        model.addAttribute("typeList",typeList);
        return "message";
    }

    @RequestMapping("/{message_type_id}/list")
    public String MessageList(HttpServletRequest request,
                              @PathVariable("message_type_id") Integer type_id,
                              Model model){
        //在token中获取用户id
        Integer user_id = tokenUtils.getUserId(tokenUtils.getToken(request));
        //获取消息类型
        Message msg_type = messageService.getType(type_id);
        //根据消息类型和用户编号拉取用户的消息列表
        List<Message> messageList = messageService.getMsgByUserAndType(user_id, type_id);

        model.addAttribute("message_type",msg_type.getMsg_type());
        model.addAttribute("message_type_id",type_id);
        model.addAttribute("messageList",messageList);

        return "message_list";
    }
}
