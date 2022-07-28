package com.session_service.mapper;


import com.common.Entity.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface SeatMapper {
    /**
     * 通过座位Id查找座位的行列
     */
    @Select("select seat_id,seat_row,seat_column from seat where seat_id=#{seat_id}")
    Seat queryBySeatId(Integer seat_id);

    /**
     * 根据座位查找作为编号
     * @param row
     * @param column
     * @return
     */
    @Select("select seat_id,seat_row,seat_column from seat where seat_row=#{row} and seat_column=#{column}")
    Seat queryBySeat(Integer row, Integer column);

    /**
     * 根据订单号查询座位信息
     * @param order_id
     * @return
     */
    @Select("select order_detail.seat_id,seat_row,seat_column from order_detail left join seat " +
            "on order_detail.seat_id=seat.seat_id " +
            "where order_detail.order_id=#{order_id}")
    List<Seat> querySeatsByOrderId(String order_id);
}
