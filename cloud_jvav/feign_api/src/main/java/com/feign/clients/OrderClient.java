package com.feign.clients;

import com.common.Entity.Order;
import com.common.Entity.Seat;
import com.common.exception.JvavException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@FeignClient("orderservice")
public interface OrderClient {

    /**
     * 根据订单Id获取订单信息
     * @param order_id
     * @return
     */
    @PostMapping("/order/get/{order_id}")
    Order getOrderByOrderId(@PathVariable("order_id") String order_id);

    @PostMapping("/order/all/{user_id}")
    public List<Order>  getOrdersByUserId(@PathVariable("user_id") Integer user_id);

    @PostMapping("/order/invalid/get/{order_id}")
    void delectSeatSelectedByOrderId(@PathVariable("order_id") String order_id);

    /**
     * 查询用户是否存在未支付订单
     * @param user_id
     * @return
     */
    @PostMapping("/order/notpay/{user_id}")
    Order getNotPayOrderByUserId(@PathVariable("user_id") int user_id) throws JvavException;

    /**
     * 插入订单
     * @param selected 座位列表
     * @param session_id 场次Id
     * @return
     */
    @PostMapping("/order/put")
    String addOrder(@RequestBody HashMap<String, List<Integer>> selected,
                    @RequestParam("session_id") Integer session_id,
                    @RequestParam("user_id")    Integer user_id);

    /**
     * 根据订单号修改订单状态为指定状态
     * @param order_id
     * @param code
     */
    @PostMapping("/order/status/set")
    void setOrderStatusByOrderId(@RequestParam("order_id") String order_id,
                                 @RequestParam("code") String code);

    /**
     * 根据订单号获取订单座位
     * @param order_id
     * @return
     */
    @PostMapping("/seat/order/get")
    List<Seat> getSeatsByOrderId(@RequestParam("order_id") String order_id);

    /**
     * 插入退款信息
     * @param refund_request_no
     * @param total
     * @param refund_reason
     */
    @PostMapping("/refund/put")
    void addRefund(@RequestParam("refund_request_no") String refund_request_no,
                   @RequestParam("total") float total,
                   @RequestParam("refund_reason") String refund_reason,
                   @RequestParam("order_id") String order_id);


    /**
     * 根据退款请求号修改退款状态
     * @param refund_request_no
     */
    @PostMapping("/refund/set/status")
    void setRefundStatusByNo(@RequestParam("refund_request_no") String refund_request_no,
                             @RequestParam("status_no") int status_no);
}
