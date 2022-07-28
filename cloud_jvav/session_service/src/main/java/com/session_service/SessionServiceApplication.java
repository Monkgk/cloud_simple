package com.session_service;

import com.feign.clients.CinemaClient;
import com.feign.clients.FilmClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableFeignClients(clients = {FilmClient.class,CinemaClient.class})
public class SessionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SessionServiceApplication.class,args);
    }
    //配置时区
    @PostConstruct
    void setTimeZone(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }
}
