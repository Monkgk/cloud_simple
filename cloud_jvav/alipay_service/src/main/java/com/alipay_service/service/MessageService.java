package com.alipay_service.service;

import com.common.Entity.Message;

import java.util.List;

public interface MessageService {
    /**
     * 获得消息类型列表
     * @return
     */
    List<Message> getTypeList();

    /**
     *根据类型编号获取消息类型
     * @param type_id
     * @return
     */
    Message getType(Integer type_id);

    /**
     * 根据消息类型和用户编号拉取用户的消息列表
     * @param user_id
     * @param type_id
     * @return
     */
    List<Message> getMsgByUserAndType(Integer user_id, Integer type_id);

    /**
     * 添加消息
     * @param message_title
     * @param message_type_id
     * @param message
     * @param user_id
     * @param orderNo
     */
    void addOrderMessageByUser(String message_title, Integer message_type_id, String message,Integer user_id, String orderNo);
}
