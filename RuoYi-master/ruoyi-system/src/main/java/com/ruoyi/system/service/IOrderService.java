package com.ruoyi.system.service;

import com.common.Entity.Order;

import java.util.List;

public interface IOrderService {
    /**
     * 返回全部订单列表
     * @return
     */
    List<Order> selectOrderList(Order orders);
}
