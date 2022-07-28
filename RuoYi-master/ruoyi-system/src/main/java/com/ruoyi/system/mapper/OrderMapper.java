package com.ruoyi.system.mapper;

import com.common.Entity.Order;

import java.util.List;

/**
 * 订单查询 数据层
 */
//@Mapper
public interface OrderMapper {
    /**
     * 查询相应订单
     *
     * @return
     */
//    @Select("select order_id,user_id,total,order_status,create_time,cinema_name,film_name " +
//            "from orders t1 " +
//            "left join session t2 on t1.session_id=t2.session_id " +
//            "left join cinema t3 on t2.cinema_id=t3.cinema_id " +
//            "left join film t4 on t2.film_id=t4.film_id ")
    List<Order> selectOrderList(Order orders);

    /**
     * 查询所有订单
     * @return
     */
    List<Order> selectOrderAll();
}
