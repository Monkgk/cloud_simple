package com.alipay_service;

import com.feign.clients.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableFeignClients(clients = {OrderClient.class, MessageClient.class})
public class AliPayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliPayServiceApplication.class,args);
    }
    //配置时区
    @PostConstruct
    void setTimeZone(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }
}
