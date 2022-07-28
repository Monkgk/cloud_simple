package com.alipay_service.service.impl;

import com.alipay_service.service.MessageService;
import com.common.Entity.Message;
import com.feign.clients.MessageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageClient messageClient;
    @Override
    public List<Message> getTypeList() {
        return messageClient.getTypeList();
    }

    @Override
    public Message getType(Integer type_id) {
        return messageClient.getTypeByTypeId(type_id);
    }

    @Override
    public List<Message> getMsgByUserAndType(Integer user_id, Integer type_id) {
        return messageClient.getMsgByUserAndType(user_id,type_id);
    }

    @Override
    public void addOrderMessageByUser(String message_title, Integer message_type_id, String message, Integer user_id, String orderNo) {
        messageClient.addOrderMessageByUser(message_title,message_type_id,message,user_id,orderNo);
    }
}
