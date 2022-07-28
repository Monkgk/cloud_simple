package com.alipay_service.service.impl;


import com.alipay_service.Vo.AliPayVo;
import com.alipay.api.AlipayApiException;
import com.alipay_service.service.AlipayService;
import com.alipay_service.utils.AliPayUtils;
import com.feign.clients.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;


@Service
public class AlipayServiceImpl implements AlipayService {
    @Autowired
    OrderClient orderClient;
    @Autowired
    AliPayUtils aliPayUtils;

    @Override
    public String aliPay(String money, String subject , String time_expire, String out_trade_no) throws IOException, AlipayApiException {
        //调用支付宝支付
        String result = aliPayUtils.pay(money,subject ,time_expire,out_trade_no);
        //输出
//        out.println(result);
//        log.info("返回结果={}",result);
        return result;
    }

    @Override
    public HashMap<String,Object> getAliPay(String out_trade_no) throws AlipayApiException {
        HashMap<String,Object> returnData = aliPayUtils.query(out_trade_no);
        return returnData;
    }

    @Override
    public boolean refund(String order_id, Float total, String refund_request_no) throws AlipayApiException {
        Integer result = aliPayUtils.refund(order_id,total,refund_request_no);
        if(result.equals(1)){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void closeAliPay(String order_id) throws AlipayApiException {
        aliPayUtils.close(order_id);
    }

}
