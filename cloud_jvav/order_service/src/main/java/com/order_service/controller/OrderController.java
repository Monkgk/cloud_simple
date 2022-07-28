package com.order_service.controller;

import com.common.Entity.Order;
import com.common.exception.JvavException;
import com.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService    orderService;

    @RequestMapping("/get/{order_id}")
    public Order getOrderByOrderId(@PathVariable("order_id") String order_id){
        return orderService.getOrderByOrderId(order_id);
    }

    @RequestMapping("/invalid/get/{order_id}")
    public void delectSeatSelectedByOrderId(@PathVariable("order_id") String order_id){
        orderService.delectSeatSelectedByOrderId(order_id);
    }


    @RequestMapping("/all/{user_id}")
    public List<Order>  getOrdersByUserId(@PathVariable("user_id")  Integer user_id) throws JvavException {
        return orderService.getOrdersByUserId(user_id);
    }

    @RequestMapping("/notpay/{user_id}")
    public Order    getNotPayOrderByUserId(@PathVariable("user_id") int user_id) throws JvavException{
        return orderService.queryNotPayOrderByUserId(user_id);
    }

    @RequestMapping("/put")
    public String   addOrder(@RequestBody HashMap<String, List<Integer>> selected,
                             Integer session_id,
                             Integer user_id) throws JvavException {
        return orderService.addOrder(selected, session_id,user_id);
    }

    @RequestMapping("/status/set")
    public void setOrderStatus(String order_id,String code){
        orderService.setOrderStatuById(order_id,code);
    }
}
