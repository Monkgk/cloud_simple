package com.feign.clients;

import com.common.Entity.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("messageservice")
public interface MessageClient {

    /**
     * 获得消息类型列表
     * @return
     */
    @PostMapping("/message/list")
    List<Message>   getTypeList();

    /**
     * 根据消息Id获取消息类型信息
     * @param type_id
     * @return
     */
    @PostMapping("/message/type/{type_id}")
    Message getTypeByTypeId(@PathVariable("type_id") Integer type_id);

    /**
     * 根据消息类型和用户编号拉取用户的消息列表
     * @param user_id
     * @param type_id
     * @return
     */
    @PostMapping("/message/user/{user_id}/{type_id}")
    List<Message> getMsgByUserAndType(@PathVariable("user_id") Integer user_id,
                                      @PathVariable("type_id") Integer type_id);

    /**
     * 插入消息
     * @param message_title
     * @param message_type_id
     * @param message
     * @param user_id
     * @param orderNo
     */
    @PostMapping("/message/put")
    void addOrderMessageByUser(@RequestParam("message_title") String message_title,
                          @RequestParam("message_type_id") Integer message_type_id,
                          @RequestParam("message") String message,
                          @RequestParam("user_id") Integer user_id,
                          @RequestParam("orderNo") String orderNo);
}
