package com.page_service.Config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置文件类
 * 声明队列、交换机
 */
@Configuration
public class TtlQueueConfig {
    //订单普通交换机
    public static final String ORDER_EXCAHGNE = "order_exchange";
    //订单死信交换机
    private static final String EXPIRE_EXCHANGE = "expire_exchange";
    //订单普通队列
    public static final String ORDER_QUEUE = "order_queue";
    //订单死信队列
    private static final String EXPIRE_QUEUE = "expire_queue";

    //声明普通交换机
    @Bean("OrderExcange")
    public DirectExchange OrderExchange(){
        return new DirectExchange(ORDER_EXCAHGNE);
    }
    //声明死信交换机
    @Bean("ExpireExcange")
    public DirectExchange ExpireExchange(){
        return new DirectExchange(EXPIRE_EXCHANGE);
    }
    //声明普通队列
    @Bean("OrderQueue")
    public Queue OrderQueue(){
        Map<String,Object> arguments = new HashMap<>();
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",EXPIRE_EXCHANGE);
        //设置死信RoutingKey
        arguments.put("x-dead-letter-routing-key","orderExpire");
        //设置过期时间TTL 1s=1000ms  15min=900000ms
        arguments.put("x-message-ttl",900000);

        return QueueBuilder.durable(ORDER_QUEUE).withArguments(arguments).build();
    }
    //声明死信队列
    @Bean("ExpireQueue")
    public Queue ExpireQueue(){
        return QueueBuilder.durable(EXPIRE_QUEUE).build();
    }

    //绑定，设置队列到交换机并设置路由键
    @Bean
    public Binding OrderQueueBind(@Qualifier("OrderQueue") Queue OrderQueue,
                                  @Qualifier("OrderExcange") DirectExchange OrderExchange){
        return BindingBuilder.bind(OrderQueue).to(OrderExchange).with("orderInsert");
    }
    @Bean
    public Binding ExpireQueueBind(@Qualifier("ExpireQueue") Queue ExpireQueue,
                                  @Qualifier("ExpireExcange") DirectExchange ExpireExcange){
        return BindingBuilder.bind(ExpireQueue).to(ExpireExcange).with("orderExpire");
    }
}
