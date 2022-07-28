package com.common.Vo;

import com.common.exception.JvavException;
import lombok.Getter;

@Getter
public enum OrderStatus {
    /**
     * 已取票
     */
    ORDER_TAKE("1","已取票"),
    /**
     * 未取票
     */
    ORDER_NOTTAKE("2","未取票"),
    /**
     * 已退票
     */
    ORDER_BACK("3","已退票"),
    /**
     * 待支付
     */
    ORDER_STAYPAY("4","待支付"),
    /**
     * 已失效
     */
    ORDER_INVALID("5","已失效"),
    /**
     * 已取消
     */
    ORDER_CANCEL("6","已取消");

    private String code;   //状态码
    private String  msg;    //消息

    public void setMsg(String msg) {
        this.msg = msg;
    }

    OrderStatus(String code, String msg){
        this.code=code;
        this.msg=msg;
    }

    /**
     * 返回相应代码的枚举信息
     * @param code
     * @return
     */
    public static OrderStatus getMsg(String code) throws JvavException {
        for(OrderStatus orderStatus:OrderStatus.values()){
            if(orderStatus.getCode().equals(code)){
                return orderStatus;
            }
        }
        throw new JvavException("该订单状态码不存在");
    }
}
