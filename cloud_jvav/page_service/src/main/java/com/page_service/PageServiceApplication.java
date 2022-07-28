package com.page_service;

import com.feign.clients.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableCaching
@EnableFeignClients(clients = {FilmClient.class, UserClient.class, OrderClient.class, CinemaClient.class, SessionClient.class,AlipayClient.class,MessageClient.class})
public class PageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PageServiceApplication.class,args);
    }
    //配置时区
    @PostConstruct
    void setTimeZone(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }
}
