package com.order_service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RefundMapper {

    /**
     * 插入未退款成功的退款订单
     * @param refund_request_no 退款订单编号
     * @param price 退款金额
     */
    @Insert("insert into order_refund(refund_request_no,refund_amount,refund_status,refund_reason,order_id) values(#{refund_request_no},#{price},-1,#{refund_reason},#{order_id})")
    void insertRefund(String refund_request_no, Float price,String refund_reason,String order_id);

    /**
     * 修改退款订单状态
     * @param refund_request_no 退款订单编号
     * @param status_no 状态码
     */
    @Update("update order_refund set refund_status=#{status_no} where refund_request_no=#{refund_request_no}")
    void UpdateRefundStatus(String refund_request_no,int status_no);

}
