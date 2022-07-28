package com.order_service.service.impl;

import com.common.Entity.Seat;
import com.order_service.mapper.SeatMapper;
import com.order_service.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    SeatMapper  seatMapper;

    @Override
    public List<Seat> getSeatsByOrderId(String order_id) {
        return seatMapper.querySeatsByOrderId(order_id);
    }
}
