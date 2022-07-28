package com.feign.clients;

import com.alipay.api.AlipayApiException;
import com.common.Entity.Order;
import lombok.experimental.PackagePrivate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@FeignClient("alipayservice")
public interface AlipayClient {

    /**
     * 发起支付请求
     * @param order
     * @return
     */
    @PostMapping("/alipay/pay")
    public String pay(@RequestBody Order order);

    /**
     * 根据订单号获取订单
     * @param out_trade_no
     * @return
     * @throws AlipayApiException
     */
    @PostMapping("/alipay/get/{out_trade_no}")
    HashMap<String,Object>  getAliPay(@PathVariable("out_trade_no") String out_trade_no) throws AlipayApiException;

    /**
     * 向支付宝发起退款请求
     * @param order_id 需要退款的订单编号
     * @param total 退款金额
     * @param refund_request_no 退款请求号
     * @return ture退款成功，false退款失败
     */
    @PostMapping("/alipay/refund")
    boolean refund(@RequestParam("order_id") String order_id,
                   @RequestParam("total") Float total,
                   @RequestParam("refund_request_no") String refund_request_no) throws AlipayApiException;


    /**
     * 关闭支付宝订单支付
     * @param out_trade_no 需要关闭的商户订单号
     */
    @PostMapping("/alipay/close/{out_trade_no}")
    public void closeAliPay(@PathVariable("out_trade_no") String out_trade_no) throws AlipayApiException;

}
