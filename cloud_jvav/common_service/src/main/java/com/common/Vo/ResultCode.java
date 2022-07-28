package com.common.Vo;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESSS(2000,"成功响应"),
    LOGIN_SUCCESS(2001,"登录成功"),
    REGISTRY_SUCCESS(2002,"注册成功"),
    LOGIN_FAIL(1001,"登录失败"),
    REGISTRY_FAIL(1002,"注册失败"),
    FAIL(1004,"操作失败");

    private Integer code;   //状态码
    private String  msg;    //消息

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultCode(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }
}
