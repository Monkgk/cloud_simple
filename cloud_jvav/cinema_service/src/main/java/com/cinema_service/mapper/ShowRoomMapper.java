package com.cinema_service.mapper;


import com.common.Entity.ShowRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShowRoomMapper {
    //根据放映厅编号room_id查找放映厅信息
    @Select("select room_name,room_id from showroom where room_id=#{room_id}")
    ShowRoom queryRoomByRoomId(Integer room_id);

    //根据放映厅编号room_id查找放映厅名称
    @Select("select room_name from showroom where room_id=#{room_id}")
    String  queryRoomNameByRoomId(Integer room_id);
}
