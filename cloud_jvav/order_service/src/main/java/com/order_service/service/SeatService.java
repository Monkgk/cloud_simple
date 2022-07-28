package com.order_service.service;

import com.common.Entity.Seat;

import java.util.List;

public interface SeatService {
    /**
     * 根据订单号查询座位信息
     * @param order_id
     * @return
     */
    List<Seat> getSeatsByOrderId(String order_id);
}
