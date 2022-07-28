package com.page_service.service;

import com.alipay.api.AlipayApiException;
import com.common.Entity.Order;
import com.page_service.utils.RedisUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public interface PayService {

    /**
     * 提交支付请求
     * @param order
     * @return
     */
    String PayMent(Order order);

    /**
     * 查询订单信息
     * @param order_id
     * @return
     * @throws AlipayApiException
     */
    HashMap<String, Object> query(String order_id) throws AlipayApiException;

    /**
     * 在支付宝关闭交易
     * @param order_id
     * @throws AlipayApiException
     */
    void close(String order_id) throws AlipayApiException;

    /**
     * 在数据库添加退款信息
     * @param refund_request_no
     * @param total
     * @param refund_reason
     */
    void addRefund(String refund_request_no, float total, String refund_reason,String order_id);

    /**
     * 向支付宝发起退款
     * @param orderId
     * @param total
     * @param refund_request_no
     * @return
     */
    boolean refund(String orderId, float total, String refund_request_no) throws AlipayApiException;

    /**
     * 修改退款状态为成功
     * @param refund_request_no
     */
    void setRefundToSuccess(String refund_request_no);
}
