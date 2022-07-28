package com.page_service.service;

import com.common.Entity.Order;
import com.common.Entity.Seat;
import com.common.Vo.ResultVo;
import com.common.exception.JvavException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface OrderService {
    /**
     * 根据用户Id查找所有订单
     * @param user_id
     * @return
     */
    List<Order> getOrderByUserId(Integer user_id);

    /**
     * 提交未支付订单
     * @param data
     * @param request
     * @return
     */
    ResultVo putOrder(Map<String, Object> data, HttpServletRequest request) throws JvavException;

    /**
     * 根据订单Id获取订单信息
     * @param orderId
     * @return
     */
    Order getOrderByOrderId(String orderId);

    /**
     * 根据订单号修改订单状态为指定状态
     * @param out_trade_no
     * @param code
     */
    void setOrderStatuById(String out_trade_no, String code);

    /**
     * 根据订单编号解锁座位
     * @param order_id
     * @return
     */
    void delectSeatSelectedByOrderId(String order_id);

    /**
     * 根据用户Id获取用户的未支付订单
     * @param user_id
     * @return
     */
    Order getNotPayOrderByUserId(Integer user_id) throws JvavException;

    /**
     * 根据订单Id获取订单购买的座位信息
     * @param order_id
     * @return
     */
    List<Seat> getSeatsByOrderId(String order_id);

}
