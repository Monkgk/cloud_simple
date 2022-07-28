package com.page_service.service.impl;

import com.common.Entity.Order;
import com.common.Entity.Seat;
import com.common.Util.SessionUtils;
import com.common.Vo.ResultCode;
import com.common.Vo.ResultVo;
import com.common.exception.JvavException;
import com.feign.clients.OrderClient;
import com.page_service.service.OrderService;
import com.page_service.utils.TokenUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderClient orderClient;
    @Autowired
    TokenUtils  tokenUtils;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<Order> getOrderByUserId(Integer user_id) {
        return orderClient.getOrdersByUserId(user_id);
    }

    @Override
    public ResultVo putOrder(Map<String, Object> data, HttpServletRequest request) throws JvavException {
        //List中的值为[行，列]
        HashMap<String,List<Integer>> selected = (HashMap<String, List<Integer>>) data.get("selected");
        Integer sessionId = (Integer) data.get("sessionId");
        //获取当前用户编号
        Integer user_id = SessionUtils.getCurrentUser().getUser_id();
//        Integer user_id = null;
        if (user_id==null){
            user_id = tokenUtils.getUserId(tokenUtils.getToken(request));
        }
        //判断数据是否已经存在一条未支付订单
        Order NotPayOrder = orderClient.getNotPayOrderByUserId(user_id);
        if(NotPayOrder!=null){
            //订单已存在，跳转到支付页面
            return ResultVo.returnFail(ResultCode.FAIL);
        }
        //向数据库添加订单信息
        String  order_id    =   orderClient.addOrder(selected,sessionId,user_id);
        //向普通订单队列中添加订单编号
        rabbitTemplate.convertAndSend("order_exchange","orderInsert",order_id);
        //成功添加数据，跳转到支付页面
        return ResultVo.success(ResultCode.SUCCESSS);
    }

    @Override
    public Order getOrderByOrderId(String orderId) {
        return orderClient.getOrderByOrderId(orderId);
    }

    @Override
    public void setOrderStatuById(String out_trade_no, String code) {
        orderClient.setOrderStatusByOrderId(out_trade_no,code);
    }

    @Override
    public void delectSeatSelectedByOrderId(String order_id) {
        orderClient.delectSeatSelectedByOrderId(order_id);
    }

    @Override
    public Order getNotPayOrderByUserId(Integer user_id) throws JvavException {
        return orderClient.getNotPayOrderByUserId(user_id);
    }

    @Override
    public List<Seat> getSeatsByOrderId(String order_id) {
        return orderClient.getSeatsByOrderId(order_id);
    }
}
