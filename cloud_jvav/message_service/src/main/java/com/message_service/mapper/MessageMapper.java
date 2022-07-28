package com.message_service.mapper;

import com.common.Entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    //根据用户Id查找消息信息
    @Select("select msg_id,msg_type_id,msg_content,user_id from message where user_id=#{user_id}")
    List<Message>   queryByUserId(Integer   user_id);

    //根据用户Id和消息类型查找消息,按时间降序排序
    @Select("select msg_id,msg_title,msg_type_id,msg_content,user_id,order_id,create_time from message where msg_type_id=#{type_id} and user_id=#{user_id} order by create_time desc")
    List<Message> queryByUserIdAndType(Integer user_id, Integer type_id);

    //获取消息类型信息
    @Select("select msg_type_id,msg_type from message_type order by msg_type_id")
    List<Message> queryTypeList();

    //根据类型编号获取消息类型
    @Select("select msg_type_id,msg_type from message_type where msg_type_id=#{msg_type_id}")
    Message queryTypeById(Integer msg_type_id);

    //添加交易通知
    @Insert("insert into message(msg_title,msg_type_id,msg_content,user_id,order_id) " +
            "values(#{message_title},#{message_type_id},#{message},#{user_id},#{orderNo})")
    void insertOrderMessageByUser(String message_title, Integer message_type_id, String message,Integer user_id, String orderNo);
}
