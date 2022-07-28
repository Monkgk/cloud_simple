package com.page_service.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.common.Entity.Order;
import com.common.Vo.AliPayVo;
import com.feign.clients.OrderClient;
import com.page_service.Config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 支付宝业务
 */
@Component
public class AliPayUtils {
    @Autowired
    OrderClient orderClient;

    //商户信息
    private final static AlipayClient alipayClient = new DefaultAlipayClient(
            AlipayConfig.gatewayUrl,
            AlipayConfig.app_id,
            AlipayConfig.merchant_private_key,
            AlipayConfig.format,
            AlipayConfig.charset,
            AlipayConfig.alipay_public_key,
            AlipayConfig.sign_type
    );

    /**
     * 支付宝支付订单
     * @param money 金额
     * @param subject 订单标题，主题
     * @param time_expire 支付过期时间
     * @param out_trade_no 商户订单号
     * @return
     * @throws AlipayApiException
     */
    public String pay(String money, String subject , String time_expire, String out_trade_no) throws AlipayApiException {
        //设置请求参数
//        AlipayTradeAppPayRequest aliPayRequest = new AlipayTradeAppPayRequest();// APP支付
//        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();  // 网页支付
        AlipayTradeWapPayRequest aliPayRequest = new AlipayTradeWapPayRequest();  //移动h5
        aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);

//        //商户订单号，后台可以写一个工具类生成一个订单号，必填
//        String order_number = new String(System.currentTimeMillis()+"");
//        //付款金额，从前台获取，必填
//        String total_amount = new String(money);
//        //订单名称，必填
//        String subject = new String("订单");
//        //订单超时时间
////        String timout_express = "15m";//相对超时时间
//        String time_expire = "";//绝对超时时间 yyyy-MM-dd HH:mm:ss

        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(money);
        model.setBody(subject);
        model.setTimeExpire(time_expire);
        model.setProductCode("QUICK_WAP_WAY");
//        //商品订单号
//        aliPayVo.setOut_trade_no(out_trade_no);
//        //付款金额
//        aliPayVo.setTotal_amount(money);
//        //订单超时的绝对时间
//        aliPayVo.setTime_expire(time_expire);
//        //订单名称
//        aliPayVo.setSubject(subject);
//        //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
//        aliPayVo.setProduct_code("QUICK_MSECURITY_PAY");

        //回调地址
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);
        aliPayRequest.setReturnUrl(AlipayConfig.return_url);
//        System.out.println(JSON.toJSONString(aliPayVo));
        aliPayRequest.setBizModel(model);

        //请求
        String result = "";

        result = alipayClient.pageExecute(aliPayRequest).getBody();

        return result;
    }

    /**
     * 支付宝查询订单
     * @param out_trade_no 商户订单号
     * @return
     * @throws AlipayApiException
     */
    public HashMap<String, Object> query(String out_trade_no) throws AlipayApiException {
        HashMap<String,Object> returnData = new HashMap<>();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", out_trade_no);
        //bizContent.put("trade_no", "2014112611001004680073956707");
        request.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            //判断交易结果,暂时没有处理
            switch(response.getTradeStatus()){
                //交易结束并不可退款
                case "TRADE_FINISHED":
                    break;
                //交易支付成功
                case "TRADE_SUCCESS":
                    break;
                //未付款交易超时关闭或支付完成后全额退款
                case "TRADE_CLOSED":
                    break;
                //交易创建并等待买家付款
                case "WAIT_BUYER_PAY":
                    break;
                default:
                    break;
            }
            //放入订单金额
            returnData.put("TotalAmount",response.getTotalAmount());
            //放入订单日期
            returnData.put("SendPayDate",response.getSendPayDate());
            //放入订单状态
            returnData.put("TradeStatus",response.getTradeStatus());
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return returnData;
    }

    /**
     * 支付宝退款
     * @param out_trade_no 退款订单号
     * @param refund_amount 退款金额
     * @param out_request_no 退款请求号
     * @return -1不成功，1为成功
     * @throws AlipayApiException
     */
    public Integer refund(String out_trade_no, Float refund_amount, String out_request_no) throws AlipayApiException {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        JSONObject bizContent = new JSONObject();

        //判空
        if((out_trade_no!=""&&!out_trade_no.isEmpty())
                &&(refund_amount!=null&&refund_amount.compareTo(0f)!=-1)
                &&(out_request_no!=""&&!out_request_no.isEmpty())) {
            //退款订单号
            bizContent.put("out_trade_no", out_trade_no);
            //退款金额
            bizContent.put("refund_amount", refund_amount);
            //退款请求号，保证其唯一，支付宝会保证同样的退款请求号多次请求只会退一次。
            bizContent.put("out_request_no", out_request_no);
        }else{
            //退款失败
            return -1;
        }

        //// 返回参数选项，按需传入
        //JSONArray queryOptions = new JSONArray();
        //queryOptions.add("refund_detail_item_list");
        //bizContent.put("query_options", queryOptions);

        request.setBizContent(bizContent.toString());
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("退款调用成功");
            return 1;
        } else {
            System.out.println("退款调用失败");
            return -1;
        }
    }

    /**
     * 关闭订单，需要支付宝订单的状态处于等待支付
     * @param out_trade_no 需要关闭的商户订单号
     * @return true成功，false失败
     * @throws AlipayApiException
     */
    public boolean close(String out_trade_no) throws AlipayApiException {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", out_trade_no);
        request.setBizContent(bizContent.toString());
        AlipayTradeCloseResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("关闭订单调用成功");
            return true;
        } else {
            System.out.println("关闭订单调用失败");
            return false;
        }
    }

    /**
     * 将request中的参数转换成Map
     *
     * @param request
     * @return
     */
    public Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();
        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;
            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }
        return retMap;
    }
    /**
     * 把map转为对象
     *
     * @param params
     * @return
     */
    public AliPayVo buildAlipayNotifyParam(Map<String, String> params) {
        String json = JSON.toJSONString(params);
        return JSON.parseObject(json, AliPayVo.class);
    }

    /**
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
     * 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     *
     * @param params
     * @throws AlipayApiException
     */
    public void check(Map<String, String> params) throws AlipayApiException {
        String outTradeNo = params.get("out_trade_no");
        Order order    =   orderClient.getOrderByOrderId(outTradeNo);

        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        if (order==null) {
            throw new AlipayApiException("out_trade_no错误");
        }

        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        if (order.getTotal()!=Float.valueOf(params.get("total_amount"))) {
            throw new AlipayApiException("error total_amount");
        }

        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
        // 第三步可根据实际情况省略

        // 4、验证app_id是否为该商户本身。
        if (!params.get("app_id").equals(AlipayConfig.app_id)) {
            throw new AlipayApiException("app_id不一致");
        }
    }
}
