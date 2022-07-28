package com.order_service.service.impl;


import com.order_service.mapper.RefundMapper;
import com.order_service.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
    private RefundMapper refundMapper;

    @Override
    public void addRefund(String refund_request_no, Float price,String refund_reason,String order_id) {
        refundMapper.insertRefund(refund_request_no,price,refund_reason,order_id);
    }

    @Override
    public void setRefundStatus(String refund_request_no, int status_no) {
        refundMapper.UpdateRefundStatus(refund_request_no, status_no);
    }

}
