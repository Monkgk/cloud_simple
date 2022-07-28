package com.message_service.controller;


import com.common.Entity.Message;
import com.message_service.service.MessageService;
import com.message_service.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private MessageService messageService;

    @RequestMapping("/list")
    public List<Message> Message(){
        //获取消息类型列表
        return  messageService.getTypeList();
    }

    /**
     * 根据消息Id获取消息类型信息
     * @param type_id
     * @return
     */
    @RequestMapping("/type/{type_id}")
    public Message  getTypeByTypeId(@PathVariable("type_id") Integer type_id){
        return messageService.getType(type_id);
    }

    /**
     * 根据消息类型和用户编号拉取用户的消息列表
     * @param user_id
     * @param type_id
     * @return
     */
    @RequestMapping("/user/{user_id}/{type_id}")
    public List<Message> getMsgByUserAndType(@PathVariable("user_id") Integer user_id,
                                             @PathVariable("type_id") Integer type_id) {
        return messageService.getMsgByUserAndType(user_id, type_id);
    }

    /**
     * 插入订单相关消息
     * @param message_title
     * @param message_type_id
     * @param message
     * @param user_id
     * @param orderNo
     */
    @RequestMapping("/put")
    public void addOrderMessageByUser(@RequestParam("message_title") String message_title,
                                      @RequestParam("message_type_id") Integer message_type_id,
                                      @RequestParam("message") String message,
                                      @RequestParam("user_id") Integer user_id,
                                      @RequestParam("orderNo") String orderNo){
        messageService.addOrderMessageByUser(message_title, message_type_id, message, user_id, orderNo);
    }
}
