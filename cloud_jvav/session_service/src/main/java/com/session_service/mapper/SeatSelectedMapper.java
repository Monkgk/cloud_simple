package com.session_service.mapper;



import com.common.Entity.Seat;
import com.common.Entity.SeatSelected;
import com.session_service.utils.Provider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SeatSelectedMapper {
    /**
     *通过场次编号session_id查找该场次的已被选座位编号seat_id
     */
    @Select("select seat_id from seat_selected where session_id=#{session_id}")
    List<SeatSelected> queryBySessionId(Integer session_id);

    /**
     * 插入新增的被购座位
     * @param session_id
     * @param seat_id
     * @return
     */
    @Insert("insert into seat_selected(seat_id,session_id) values (#{seat_id},#{session_id})")
    Integer insertInSeatSelected(Integer session_id,Integer seat_id);

    /**
     * 通过场次编号session_id和座位编号seat_id查找该座位是否已被选
     * @param session_id
     * @param seat_id
     * @return
     */
    @Select("select seat_id,session_id from seat_selected where session_id=#{session_id} and seat_id=#{seat_id}")
    List<SeatSelected> queryBySessionIdAndSeatId(Integer session_id, Integer seat_id);

    /**
     * 删除不需要锁定的座位
     * @param session_id
     * @param SeatIdList
     */
    @DeleteProvider(type = Provider.class,method = "batchDelete")
    void delectSeatSelected(@Param("session_id") Integer session_id,@Param("SeatIdList") List<Seat> SeatIdList);
}

