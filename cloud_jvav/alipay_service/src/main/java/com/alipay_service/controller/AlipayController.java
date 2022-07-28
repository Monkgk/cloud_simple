package com.alipay_service.controller;

import com.alipay.api.AlipayApiException;
import com.alipay_service.service.AlipayService;
import com.alipay_service.service.MessageService;
import com.alipay_service.utils.AliPayUtils;
import com.common.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/alipay")
public class AlipayController {
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private MessageService messageService;

    @RequestMapping("/pay")
    @ResponseBody
    public String payMent(@RequestBody Order order){
        //订单发起时间时间加上5分钟作为支付过期时间
        Long time_expire_long = new Date().getTime()+5*60*1000;
        //转成指定的格式，time_expire:绝对超时时间 yyyy-MM-dd HH:mm:ss
        String time_expire = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time_expire_long));
        try{
            String result = alipayService.aliPay(String.valueOf(order.getTotal()),"Jvav电影票",time_expire, order.getOrder_id());
            return result;
        } catch (IOException e){
            e.printStackTrace();
        } catch (AlipayApiException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/get/{out_trade_no}")
    public HashMap<String,Object> getAliPay(@PathVariable("out_trade_no") String out_trade_no) throws AlipayApiException {
         return alipayService.getAliPay(out_trade_no);
    }

    @RequestMapping("/refund")
    public boolean refund(String order_id, Float total, String refund_request_no) throws AlipayApiException {
        return alipayService.refund(order_id, total, refund_request_no);
    }

    @RequestMapping("/close/{out_trade_no}")
    public void closeAliPay(@PathVariable("out_trade_no") String out_trade_no) throws AlipayApiException {
        alipayService.closeAliPay(out_trade_no);
    }


}
