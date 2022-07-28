package com.page_service.service.impl;

import com.alipay.api.AlipayApiException;
import com.common.Entity.Order;
import com.common.Vo.OrderStatus;
import com.feign.clients.AlipayClient;
import com.feign.clients.MessageClient;
import com.feign.clients.OrderClient;
import com.page_service.Handlers.WebSocketHandlers;
import com.page_service.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    AlipayClient alipayClient;
    @Autowired
    OrderClient orderClient;
    @Autowired
    MessageClient messageClient;

    @Override
    public String PayMent(Order order) {
        return alipayClient.pay(order);
    }

    @Override
    public HashMap<String, Object> query(String order_id) throws AlipayApiException {
        return alipayClient.getAliPay(order_id);
    }

    @Override
    public void close(String order_id) throws AlipayApiException {
        alipayClient.closeAliPay(order_id);
    }

    @Override
    public void addRefund(String refund_request_no, float total, String refund_reason,String order_id) {
        orderClient.addRefund(refund_request_no,total,refund_reason,order_id);
    }

    @Override
    public boolean refund(String orderId, float total, String refund_request_no) throws AlipayApiException {
        return alipayClient.refund(orderId,total,refund_request_no);
    }

    @Override
    public void setRefundToSuccess(String refund_request_no) {
        orderClient.setRefundStatusByNo(refund_request_no,1);
    }


}
