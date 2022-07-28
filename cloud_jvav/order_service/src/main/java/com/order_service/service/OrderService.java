package com.order_service.service;

import com.common.Entity.Order;
import com.common.exception.JvavException;

import java.util.HashMap;
import java.util.List;

public interface OrderService {
    /**
     * 根据用户Id获取订单
     * @param user_id
     * @return
     */
    List<Order> getOrdersByUserId(Integer user_id) throws JvavException;

    /**
     * 根据用户Id获取未支付订单
     * @param user_id
     * @return
     */
    Order queryNotPayOrderByUserId(int user_id) throws JvavException;


    /**
     * 向数据库中添加订单,成功返回订单号，失败抛出异常
     * @param selected
     * @param sessionId
     * @return
     */
    String addOrder(HashMap<String, List<Integer>> selected, Integer sessionId,Integer user_id) throws JvavException;

    /**
     * 根据订单id获取订单
     * @param order_id
     * @return
     */
    Order getOrderByOrderId(String order_id);

    /**
     * 根据订单编号修改订单为指定状态
     * @param orderId
     */
    void setOrderStatuById(String orderId,String status);

    /**
     * 根据订单Id解锁座位
     * @param order_id
     * @return
     */
    void delectSeatSelectedByOrderId(String order_id);
}
