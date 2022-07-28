package com.alipay_service.Vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
//@Component
public class AliPayVo {
    //商户订单号，后台可以写一个工具类生成一个订单号，必填,
    //原支付请求的商户订单号。
    private String out_trade_no;
    //付款金额，从前台获取，必填
    private String total_amount;
    //订单名称，必填
    private String subject;
    //订单超时时间
//  String timout_express = "15m";//相对超时时间
    private String time_expire;//绝对超时时间 yyyy-MM-dd HH:mm:ss

    private String product_code;

    //通知的发送时间。格式为 yyyy-MM-dd HH:mm:ss。
    private Date notify_time;

    //通知的类型
    private String notify_type;

    //通知校验ID
    private String notify_id;

    //支付宝分配给开发者的应用 ID。
    private String app_id;

    //编码格式，如 utf-8、gbk、gb2312 等。
    private String charset;

    //调用的接口版本，固定为：1.0。
    private String version;

    //商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2
    private String sign_type;

    //签名
    private String sign;

    //支付宝交易凭证号。
    private String trade_no;

    //商户业务 ID，主要是退款通知中返回退款申请的流水号。
    private String out_biz_no;

    //交易目前所处的状态。
    //WAIT_BUYER_PAY:交易创建，等待买家付款。
    //TRADE_CLOSED:未付款交易超时关闭，或支付完成后全额退款。
    //TRADE_SUCCESS:交易支付成功。
    //TRADE_FINISHED:交易结束，不可退款。
    private String trade_status;

    //其余异步通知参数
    // https://opendocs.alipay.com/open/203/105286/

}
