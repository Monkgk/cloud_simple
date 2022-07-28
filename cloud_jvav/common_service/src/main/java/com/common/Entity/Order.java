package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Order  implements Serializable {
    @Id
    private String      order_id;
    private int     session_id;
    private int     user_id;
    @Transient
    private List<Seat>  seatList;//座位信息
    @Transient
    private Session     sessionInfo;//场次信息
    private float       total;//订单总价
    private Date        create_time;//创建时间
    private long        surplus_time;//剩余时间
    private String      order_status;//1为未取票，2为已退票，3为已取票,4待支付，5已取消
    private String      order_status_msg;//存放状态信息的字符串
    private String      cinema_name;
    private String      film_name;
}
