package com.page_service.Consumer;

import com.common.Entity.Order;
import com.common.Vo.OrderStatus;
import com.page_service.service.OrderService;
import com.page_service.service.PayService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DeadLetterQueueConsumer {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;


    //接受过期消息,这里接受过期订单，修改其状态为已失效
    @RabbitListener(queues = "expire_queue")
    public void receiveDead(Message message, Channel channel)   throws Exception{
        String    order_id    = new String(message.getBody());
        //如果订单状态为待支付则修改为过期
        if(orderService.getOrderByOrderId(order_id).getOrder_status().equals(OrderStatus.ORDER_STAYPAY.getCode())){
            //修改状态为过期
            orderService.setOrderStatuById(order_id, OrderStatus.ORDER_INVALID.getCode());
            //删除已选座位表中的锁定座位
            orderService.delectSeatSelectedByOrderId(order_id);
        }
        //支付宝关闭订单
//        if(payService.query(order_id).get("TradeStatus").equals("WAIT_BUYER_PAY")){
//            payService.close(order_id);
//        }
    }
}
