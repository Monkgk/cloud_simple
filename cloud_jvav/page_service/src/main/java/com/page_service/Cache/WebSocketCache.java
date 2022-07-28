package com.page_service.Cache;


import com.page_service.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSocketCache {
    @Autowired
    RedisUtils redisUtils;

    //在redis中的set键值
    public static final String WSKEY = "websocket";

    /**
     * 判断用户是否已经连接websocket
     *
     * @param value
     * @return ture代表已连接，false未连接
     */
    public boolean Has(Object value){
        return redisUtils.sHasKey(WSKEY,value);
    }

    /**
     * 存放user_id
     * @param value
     * @param time
     */
    public void put(Object value,Long time){
        redisUtils.sSetAndTime(WSKEY,time,value);
    }

}
