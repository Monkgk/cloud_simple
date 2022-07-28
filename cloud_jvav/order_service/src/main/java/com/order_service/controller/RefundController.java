package com.order_service.controller;

import com.order_service.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refund")
public class RefundController {
    @Autowired
    RefundService   refundService;

    @RequestMapping("/put")
    public void addRefund(String refund_request_no, float total,String refund_reason,String order_id){
        refundService.addRefund(refund_request_no, total, refund_reason,order_id);
    }

    @RequestMapping("/set/status")
    public void setRefundStatusByNo(String refund_request_no,int status_no){
        refundService.setRefundStatus(refund_request_no,status_no);
    }

}
