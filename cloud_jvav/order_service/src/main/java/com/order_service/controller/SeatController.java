package com.order_service.controller;

import com.common.Entity.Seat;
import com.order_service.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    SeatService seatService;

    /**
     * 根据订单号获取座位信息
     * @param order_id
     * @return
     */
    @RequestMapping("/order/get")
    List<Seat> getSeatsByOrderId(String order_id){
        return seatService.getSeatsByOrderId(order_id);
    }

}
