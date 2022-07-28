package com.session_service.service;

import com.common.Entity.Seat;

import java.util.List;

public interface SeatSelectedService {
    //根据场次号查询已被选的座位
    List<String> getSelectedBySessionId(Integer session_id);
    //根据场次号和座位号删除指定的锁定座位
    void delectSeatSelected(Integer session_id, List<Seat> seatList);
}
