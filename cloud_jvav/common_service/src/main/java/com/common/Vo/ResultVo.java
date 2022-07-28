package com.common.Vo;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class ResultVo<T> implements Serializable {
    private Integer code;   //状态码
    private String  msg;    //消息
    private List<T> datas;

    public ResultVo(){}

    public ResultVo(ResultCode  resultCode,List<T>  datas){
        this.code   = resultCode.getCode();
        this.msg    = resultCode.getMsg();
        this.datas  =   datas;
    }

    public ResultVo(List<T> datas){
        this(ResultCode.SUCCESSS,datas);
    }
    //    成功响应
    public static   ResultVo    success(ResultCode resultCode){
        return new ResultVo(resultCode,null);
    }
    //    失败响应
    public static   ResultVo    returnFail(ResultCode  resultCode){
        return new ResultVo(resultCode,null);
    }
}

