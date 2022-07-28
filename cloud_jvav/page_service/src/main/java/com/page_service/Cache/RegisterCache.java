package com.page_service.Cache;



import com.page_service.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterCache {
    @Autowired
    RedisUtils redisUtils;

    //在redis中的set键值
    public static final String REKEY = "register";

    /**
     * 判断用户是否已经登录
     *
     * @param value
     * @return ture代表已登录，false未登录
     */
    public boolean Has(Object value){
        return redisUtils.sHasKey(REKEY,value);
    }

    /**
     * 存放user_id
     * @param value
     * @param time
     */
    public void put(Object value,Long time){
        redisUtils.sSetAndTime(REKEY,time,value);
    }

}
