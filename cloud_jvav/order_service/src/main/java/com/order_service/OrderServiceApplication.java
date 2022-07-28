package com.order_service;

import com.feign.clients.FilmClient;
import com.feign.clients.SessionClient;
import com.feign.clients.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableFeignClients(clients = {FilmClient.class, UserClient.class, SessionClient.class})
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class,args);
    }
    //配置时区
    @PostConstruct
    void setTimeZone(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }
}
