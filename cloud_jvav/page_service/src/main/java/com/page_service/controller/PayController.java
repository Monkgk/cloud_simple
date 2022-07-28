package com.page_service.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.common.Entity.Order;
import com.common.Entity.User;
import com.common.Vo.AliPayVo;
import com.common.Vo.OrderStatus;
import com.feign.clients.AlipayClient;
import com.page_service.Config.AlipayConfig;
import com.page_service.Handlers.WebSocketHandlers;
import com.page_service.service.MessageService;
import com.page_service.service.OrderService;
import com.page_service.service.PayService;
import com.page_service.service.UserService;
import com.page_service.utils.AliPayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class PayController {
    @Autowired
    OrderService    orderService;
    @Autowired
    UserService     userService;
    @Autowired
    PayService      payService;
    @Autowired
    MessageService  messageService;
    @Autowired
    AliPayUtils     aliPayUtils;

    @RequestMapping("/pay")
    @ResponseBody
    public String payMent(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestBody Map<Object,Object> data){
        //取出订单编号
        String orderId = (String) data.get("orderId");
        //根据订单编号获取所需的订单信息
        Order orderInfo =   orderService.getOrderByOrderId(orderId);
        return payService.PayMent(orderInfo);
    }

    /**
     * 支付宝异步通知
     * @param request
     * @param response
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/alipayNotifyNotice")
//    @ResponseBody
    public void alipayNotifyNotice(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
        //将异步通知中收到的待验证所有参数都存放到map中
        final Map<String, String> params = aliPayUtils.convertRequestParamsToMap(request);
        final String paramsJson = JSON.toJSONString(params);
        System.out.println("支付宝回调：" + paramsJson);
        try {
            // 调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
                    AlipayConfig.charset, AlipayConfig.sign_type);
            if (signVerified) {
                System.out.println("支付宝回调签名认证成功");
                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验
                // 校验成功后在response中返回success，校验失败返回failure
                aliPayUtils.check(params);
                AliPayVo param = aliPayUtils.buildAlipayNotifyParam(params);
                String trade_status = param.getTrade_status();
                // 支付成功
                if (trade_status.equals("TRADE_SUCCESS")
                        || trade_status.equals("TRADE_FINISHED")) {
                    // 处理支付成功逻辑
                    try {
                        //修改订单状态为未取票
                        //支付成功修改订单状态,修改为未取票
                        orderService.setOrderStatuById(param.getOut_trade_no(), OrderStatus.ORDER_NOTTAKE.getCode());
                    } catch (Exception e) {
                        System.out.println("支付宝回调业务处理报错,params:" + paramsJson);
                    }
                } else {
                    System.out.println("没有处理支付宝回调业务，支付宝交易状态：" + trade_status + "params:" + paramsJson);
                }
                // 如果签名验证正确，立即返回success，后续业务另起线程单独处理
                // 业务处理失败，可查看日志进行补偿，跟支付宝已经没多大关系。
//                return "success";
            } else {
                System.out.println("支付宝回调签名认证失败，signVerified=false, paramsJson:" + paramsJson);
//                return "failure";
            }
        } catch (AlipayApiException e) {
            System.out.println("支付宝回调签名认证失败,paramsJson:" + paramsJson + ",errorMsg:" + e.getMessage());
//            return "failure";
        }
    }

    /**
     * 支付宝同步通知页面,成功返回
     * @param request
     * @param response
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/alipayReturnNotice")
    public String alipayReturnNotice(HttpServletRequest request, HttpServletRequest response, HttpSession httpSession) throws Exception {
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        // 调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        if (signVerified) {
            //商户订单号
            String orderNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            //支付成功修改订单状态,修改为未取票
            orderService.setOrderStatuById(orderNo, OrderStatus.ORDER_NOTTAKE.getCode());
            //根据订单号查找用户id,获取用户对象
//            User currentUser = SessionUtils.getCurrentUser();
            User user = userService.getUserByUserId(orderService.getOrderByOrderId(orderNo).getUser_id());
            Integer message_type_id = 1;
            //根据用户编号推送消息
            WebSocketHandlers.sendToUser(user.getUser_id(),message_type_id.toString());
            //编辑消息内容
            String message = "<span style=\"color:#A9A9A9;font-size:3rem\"><b style=\"color:black;\">订单号：</b>"+orderNo+"</span>"+
                    "<span style=\"color:#A9A9A9;font-size:3rem;float:left\"><b style=\"color:black;\">金额：</b>"+total_amount+"</span>";
            //添加消息到数据库
            messageService.addMessageByUserId("支付助手",message_type_id,message,user.getUser_id(),orderNo);
        } else {
            System.out.println("同步回调签名验证失败");
        }
        return "redirect:/index";
    }

}
