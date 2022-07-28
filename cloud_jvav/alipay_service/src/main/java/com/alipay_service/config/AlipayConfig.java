package com.alipay_service.config;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public final static String app_id = "2021000118658005";

    // 商户私钥，您的PKCS8格式RSA2私钥
//    @Value("${alipay.app-private-key}")
    public final static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCh1MRzhiz3YokmddFzAMKsN5W6typombCXhHdtRmOi9O/rVVVGO5za6lGOls56Gu4BA+S9a2/p2Mz6tI1IJXiTgtVgJ0YNT59czT83AJ26iICWenss1seF8JQbWePQkGmSYJJciaMCvvYTodeRcf28PDhlKiI/i21IiyiWaxB6P7XS4b7oLODDW5FddiQ4RIxmrWB9v+yioxBvaOK6SrudCZlBFhXEuSxyuYlmNLg5Rit3s7j6xfwmgTvih0BYixH2cAJWLgwjgqlf9CiDhPZbedoPVuXY9G+PTEJAemLkPO75oTiLebRzQ0KpN98D9rCsp+uyVV9598XmLXauwnJhAgMBAAECggEBAIo28zYLgbojoSmE9Mb4/ksiCjJ3H7RIlydBTtK4jH5wHbzrm7IpVdUuzUswB9cHywB499LDwMiDjsYMkKHuxuWNl3nQ8SaLO3uCHJx4XT4kOVbL8wmfwrble0lE46qMvG31Juv77Yp52Cd6UWd6aRBsimn0q12EUmjj2V1mwaL2BbmOaII8CX2jmqUhVerREdmYwQh94zs/7x3vU8nl9iQVHN3nC3VvkwsCwVK0Ae+wdV4iwAyoZDv03W5gTb9TTf19Bp3LcNDfpARsWM54d2Ir93ok/LQWBR+WulCNXKtiROAze7XmqqXsrPiwzUdwLV/MLmzOVS4a6Zl4MeGj9vECgYEA+ouWBDzHIN60zkgzn1CgtdIeYre5JiDEn6vIyF1j4MQ2OmpeYkpJ0GwfLBQLCvk9aZfXen5+PBuPL1W7G7bo8K9zTSlE5mGMEtizPOww8t/6/CdJuKmPBMOzzYfqnHELuaHaUxGwN12KydUCyy1L/PpAPCfw/UBci4BaRQYKQ9UCgYEApVq7r9YSUGTfb+0sCh7Q/Zqkr4yv6zbzuE0MHqgQ5m1d8n1B+V7ONuYgZLI1JP5rRxewrL/UsRcHUbh0OA/W4FLQgfTE5OJBbUdHz8sdOzWO/GTPFeaDElfPYr7K2hAYd+jzmqPiuTGDWYZ6vkPHJENmgAedYDS/rT/Eyj2lll0CgYEAijoKqQnTHrpf7FL/UA0OrWFgbDxKhNOdRy5iVtDXcsEt3eqjafzsXnXYeNwR/5rkm+Yu9qDlCkp2I7pbwX2XZHqX1WFIZwY4Xol50cnJc6XI9ebDEINTqWBDSciL0oW331GBf0UKAHG2SYYgxXGXM9npKIcsuLwSktBcfSpI7YUCgYEAhrEKAYlXEbWZbAq9vOCtTGcTjwwCoI76qD3aiX5NheXAiS4O95qKT53U9TFUqPW1XQunFpCMKFSh2aduw9vjJIdQ4QsI94h10xYlINZK0VgnSOC07xn92os8myrfAhHqXIKdLHIi1MfIPryUbze2l4tviwqo3x0YpX80RalBoOECgYB2iLTr9SDO76UXj1Mq9T1QvyXZaJUKQoVpgNCXrV/oDnZhjOQyrtSTeWf51j3ZXncex/WwNHWc+aXbMXUtQz03B2GzMcUXp7U+MP4f5S/GDCzZA7PkzuH06rr/KdWRfGILB4BHpKhKZLnwjRxwg1xmqt/W4vDP7Pn75E9iwXXWiw==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
//    @Value("${alipay.alipay-public-key}")
    public final static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy+oH60vozwnEQVQ4VTsU/4kV1lBcW4fvj8AClS6R2NGNOFYKB/p4tDSKGMchSTXHIAg651BVCO7S5+vdND3v6z5QVnrcKKRbIRlk3zRrkt4PTUipjtMe1vEM+aCxztZdYwE559DQFuoGwZ+YCkimEXHRMgPK+GA6aZeIWVV6vB3AKy/Z0ocLYvvGqU7BhriXWWHFyqxNyfhWaqJeo+KT0Q3PAYJIwvrbTX1qdUNCbYTJ7LCnn59xbcPOfP0JHbb6+RpJVJb9zJytxgl60kc9eTMeRXy9a2f0kcP4WLxNuR2YeHByS5fRVhgMiaj6sGF3uAMEHc0XOOqFWOCnLY0TiQIDAQAB";

    private final static String url   =   "http://gqpweb.natapp1.cc";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
//    @Value("${alipay.notifyUrl}")
    public final static String notify_url = url+"/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
//    @Value("${alipay.returnUrl}")
    public final static String return_url = url+"/alipayReturnNotice";

    // 签名方式
//    @Value("${alipay.signType}")
    public final static String sign_type = "RSA2";

    // 字符编码格式
//    @Value("${alipay.charset}")
    public final static String charset = "utf-8";

    // 支付宝网关
//    @Value("${alipay.gatewayUrl}")
    public final static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    //返回格式
//    @Value("${alipay.format}")
    public final static String format = "JSON";

    // 支付宝网关
//    @Value("${alipay.logs}")
    public static String log_path;



    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
