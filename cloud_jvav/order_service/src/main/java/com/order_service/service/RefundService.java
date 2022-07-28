package com.order_service.service;

public interface RefundService {
    /**
     * 插入未退款成功的退款订单
     * @param refund_request_no 退款请求号
     * @param price 退款金额
     */
    void addRefund(String refund_request_no, Float price,String refund_reason,String order_id);

    /**
     * 修改退款订单状态
     * @param refund_request_no 退款请求号
     * @param status_no 退款状态
     */
    void setRefundStatus(String refund_request_no,int status_no);
}
