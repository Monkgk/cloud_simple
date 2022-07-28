package com.message_service.service.impl;


import com.common.Entity.Message;
import com.message_service.mapper.MessageMapper;
import com.message_service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    /**
     * 获取消息具体列表
     * @param user_id
     * @param type_id
     * @return
     */
    @Override
    public List<Message> getMsgByUserAndType(Integer user_id, Integer type_id) {

        return messageMapper.queryByUserIdAndType(user_id,type_id);
    }

    /**
     * 获取消息类型
     * @return
     */
    @Override
    public List<Message> getTypeList() {
        return messageMapper.queryTypeList();
    }

    @Override
    public Message getType(Integer msg_type_id) {
        return messageMapper.queryTypeById(msg_type_id);
    }

    @Override
    public void addOrderMessageByUser(String message_title, Integer message_type_id, String message,Integer user_id, String orderNo) {
        messageMapper.insertOrderMessageByUser(message_title,message_type_id,message,user_id,orderNo);
    }
}
