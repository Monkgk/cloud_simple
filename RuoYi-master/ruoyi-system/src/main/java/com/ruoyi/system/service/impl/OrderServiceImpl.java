package com.ruoyi.system.service.impl;

import com.common.Entity.Order;
import com.ruoyi.system.mapper.OrderMapper;
import com.ruoyi.system.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public List<Order> selectOrderList(Order orders) {
        return orderMapper.selectOrderList(orders);
    }
}
