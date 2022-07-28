package com.alipay_service.service;

import com.alipay.api.AlipayApiException;
import com.common.Entity.Order;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝业务
 */
public interface AlipayService {
    

    /**
     * 提交支付宝订单
     * @param money 订单金额
     * @param subject 订单主题
     * @param time_expire 过期时间
     * @param out_trade_no 订单号
     * @return
     * @throws IOException
     * @throws AlipayApiException
     */
    String aliPay(String money, String subject, String time_expire, String out_trade_no) throws IOException, AlipayApiException;

    /**
     * 查询支付宝订单，返回支付宝订单的数据
     * @param out_trade_no
     * @return
     * @throws AlipayApiException
     */
    HashMap<String,Object> getAliPay(String out_trade_no) throws AlipayApiException;

    /**
     * 向支付宝发起退款请求
     * @param order_id 需要退款的订单编号
     * @param total 退款金额
     * @param refund_request_no 退款请求号
     * @return ture退款成功，false退款失败
     */
    boolean refund(String order_id, Float total, String refund_request_no) throws AlipayApiException;

    /**
     * 关闭支付宝订单支付
     * @param order_id 需要关闭的商户订单号
     */
    void closeAliPay(String order_id) throws AlipayApiException;


}
