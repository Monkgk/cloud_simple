package com.page_service.controller;

import com.alipay.api.AlipayApiException;
import com.common.Entity.Order;
import com.common.Entity.Seat;
import com.common.Entity.Session;
import com.common.Entity.User;
import com.common.Util.QRCodeUtils;
import com.common.Util.SessionUtils;
import com.common.Util.UUIDUtils;
import com.common.Vo.OrderStatus;
import com.common.Vo.ResultCode;
import com.common.Vo.ResultVo;
import com.common.exception.JvavException;
import com.feign.clients.AlipayClient;
import com.page_service.service.OrderService;
import com.page_service.service.PayService;
import com.page_service.service.SessionsService;
import com.page_service.service.UserService;
import com.page_service.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService    orderService;
    @Autowired
    UserService     userService;
    @Autowired
    SessionsService sessionsService;
    @Autowired
    PayService      payService;
    @Autowired
    TokenUtils  tokenUtils;

    /**
     *前端提交订单请求，对订单进行处理
     * @param data
     * @return
     */
    @RequestMapping("/toOrder")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultVo toOrder(@RequestBody Map<String,Object> data,
                            HttpServletRequest request) throws JvavException {
        return  orderService.putOrder(data,request);
    }

    /**
     * 返回订单确认页面，展示实现订单倒时取消
     * @return
     */
    @RequestMapping("/confirm")
    public String toConfirm(HttpServletRequest request,
                            Model model){
        //获取用户id
//        Integer     user_id     =   SessionUtils.getCurrentUser().getUser_id();
//        if (user_id==null){
            Integer user_id = tokenUtils.getUserId(tokenUtils.getToken(request));
//        }
        //获取用户信息
        User    user    =   userService.getUserByUserId(user_id);
        //获取用户手机号
        String     user_phone  =   user.getUser_phone();
        //获取未支付订单
        Order notPayOrder = null;
        try {
            notPayOrder = orderService.getNotPayOrderByUserId(user_id);
            if (notPayOrder != null){
                //获取订单的座位号
                List<Seat>  seatList    =   orderService.getSeatsByOrderId(notPayOrder.getOrder_id());
                //获取电影场次详细信息
                Session session     =   sessionsService.getSessionInfo(notPayOrder.getSession_id());

                model.addAttribute("orderInfo",notPayOrder);
                model.addAttribute("userPhone",user_phone);
                model.addAttribute("seatList",seatList);
                model.addAttribute("sessionInfo",session);

                return "order_confirm";
            }
        } catch (JvavException e) {
            //没有过期订单，返回主页面
            return "redirect:/";
        }
        return "redirect:/";
    }

    //获取取票码二维码
    @GetMapping("/detail/QR/{order_id}")
    public void  getQRCode(@PathVariable("order_id") String order_id,
                           HttpServletRequest request,
                           HttpServletResponse response){
        Order order = orderService.getOrderByOrderId(order_id);

//        StringBuffer url = request.getRequestURL();
        // 域名
//        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();

        // 再加上请求链接
//        String requestUrl = tempContextUrl + "/index";
        //二维码内容
        String data = order.getOrder_id()+System.currentTimeMillis();

        try {
            OutputStream os = response.getOutputStream();
            QRCodeUtils.encode(data, os);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return "";
    }

    @RequestMapping("/detail/{order_id}")
    public String orderDetail(@PathVariable("order_id") String order_id,
                              Model model){
        Order order = orderService.getOrderByOrderId(order_id);
        boolean test = order.getOrder_status().equals(OrderStatus.ORDER_NOTTAKE.getCode());
        if(order!=null && (order.getOrder_status().equals(OrderStatus.ORDER_NOTTAKE.getCode()))){
            //获取场次信息
            Session SessionInfo = sessionsService.getSessionInfo(order.getSession_id());
            order.setSessionInfo(SessionInfo);
            //获取订单的座位号
            List<Seat>  seatList    =   orderService.getSeatsByOrderId(order.getOrder_id());

            //获取用户信息
            User    user = userService.getUserByUserId(order.getUser_id());

//            try {
//                HashMap<String,Object> AliPayresponse = payService.query(order.getOrder_id());
//                if(AliPayresponse!=null){
//                    //存入订单金额
//                    order.setTotal(Float.valueOf((String)AliPayresponse.get("TotalAmount")));
//                    //存入时间
////                    model.addAttribute("orderDate",AliPayresponse.get("SendPayDate"));
//                }else{
//                    return "redirect:/mine/order";
//                }
//            } catch (AlipayApiException e) {
//                e.printStackTrace();
//            }

            model.addAttribute("Infos",order);
            model.addAttribute("seats",seatList);
            model.addAttribute("user_phone",user.getUser_phone());

            return "order_qrcode";
        }

        return "redirect:/mine/order";
    }

    /**
     * 获取退款订单信息
     */
    @RequestMapping("/refund/{order_id}")
    public String Refund(HttpServletRequest request,
                         @PathVariable("order_id") String order_id,
                         Model model) throws AlipayApiException {
        //获取订单信息
        Order order = orderService.getOrderByOrderId(order_id);
        //从token中解析用户Id
        int current_user_id = tokenUtils.getUserId(tokenUtils.getToken(request));
        //判断是否当前用户
        if(order!=null && order.getUser_id()==current_user_id){
            //获取场次信息
            Session session =   sessionsService.getSessionInfo(order.getSession_id());
            //获取座位信息
            List<Seat>  seatList    =   orderService.getSeatsByOrderId(order.getOrder_id());
            //请求支付宝订单信息
            HashMap<String,Object> AliPayresponse = payService.query(order.getOrder_id());


            //存入信息
            order.setSessionInfo(session);
            order.setSeatList(seatList);
            //存入订单金额
//            order.setTotal(Float.valueOf((String)AliPayresponse.get("TotalAmount")));

            //返回信息
            model.addAttribute("Infos",order);
            return "refund";
        }
        return "redirect:/mine/order";
    }

    /**
     * 处理退款业务
     */
    @RequestMapping("/toRefund")
    @ResponseBody
    public ResultVo toRefund(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestBody Map<Object,Object> data) throws AlipayApiException {
        //提取请求数据
//        Map<String, String[]> requestParams = request.getParameterMap();
//        Integer orderId = Integer.valueOf(requestParams.get("orderId")[0]);
//        String  refund_reason = requestParams.get("reason")[0];
        String orderId = (String)data.get("orderId");
        String refund_reason = (String) data.get("reason");
        if(orderId.equals(null)){
            return ResultVo.returnFail(ResultCode.FAIL);
        }
        //根据id查找订单信息
        Order order = orderService.getOrderByOrderId(orderId);
        //获取订单金额
//        Float order_price = orderService.getOrderPriceByOrderId(orderId);
        //将订单金额插入订单对象中
//        order.setTotal(order_price);
        //生成退款请求号
        String refund_request_no = UUIDUtils.randomUUID();
        System.out.println(order.getTotal());
        //插入退款信息到数据库中
        payService.addRefund(refund_request_no,order.getTotal(),refund_reason,order.getOrder_id());

        //使用订单编号向支付宝提出退款
        boolean result = payService.refund(orderId,order.getTotal(),refund_request_no);
        if(result){
            //修改退款订单状态为退款成功
            payService.setRefundToSuccess(refund_request_no);
            //修改订单状态为已退票
            orderService.setOrderStatuById(orderId,OrderStatus.ORDER_BACK.getCode());
            //删除已选座位信息
            orderService.delectSeatSelectedByOrderId(orderId);
            return ResultVo.success(ResultCode.SUCCESSS);
        }else {
            return ResultVo.returnFail(ResultCode.FAIL);
        }
    }
}
