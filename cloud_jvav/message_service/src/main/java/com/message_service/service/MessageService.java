package com.message_service.service;



import com.common.Entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMsgByUserAndType(Integer user_id, Integer type_id);

    List<Message> getTypeList();

    Message getType(Integer msg_type_id);

    void addOrderMessageByUser(String message_title, Integer message_type_id, String message,Integer user_id, String orderNo);
}
