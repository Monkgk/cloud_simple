package com.order_service.mapper;

import com.common.Entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 根据用户Id获取未支付订单
     * @param user_id
     * @return
     */
    @Select("select order_id,total,session_id,user_id,order_status,create_time from orders where user_id=#{user_id} and order_status='4'")
    Order queryNotPayOrderByUserId(Integer user_id);

    /**
     * 根据用户Id查看订单
     * @param user_id
     * @return
     */
    @Select("select orders.order_id,orders.session_id,orders.user_id,orders.order_status,orders.create_time,orders.total from orders " +
            "where orders.user_id=#{user_id} ")
    List<Order> queryByUserId(Integer user_id);

    /**
     * 根据用户新增订单(相应的在seat_selected表中添加数据)
     * @param order
     * @return
     */
    @Insert("insert into orders(order_id,session_id,total,user_id,create_time,order_status) values (#{order_id},#{session_id},#{total},#{user_id},#{create_time},#{order_status})")
    Integer insertInOrder(Order order);

    /**
     * 根据订单编号查找订单的相关信息
     * @param orderId
     * @return
     */
    @Select("select orders.order_id,session_id,user_id,create_time,order_status,total " +
            "from orders " +
            "where orders.order_id=#{orderId}")
    Order queryOrderByOrderId(String orderId);

    /**
     * 根据用户编号修改订单状态为指定状态
     * @param orderId
     * @param orderInvalid
     */
    @Update("update orders set order_status=#{orderInvalid} where order_id=#{orderId}")
    void updateOrderStatusByOrderId(String orderId, String orderInvalid);

    /**
     * 根据订单编号获取订单价格
     * @param order_id
     * @return
     */
    @Select("select total from order_price where order_id=#{order_id}")
    Float queryPriceByOrderId(String order_id);

    /**
     * 添加订单和座位的联系
     * @param order_id
     * @param seat_id
     */
    @Select("insert into order_detail(order_id,seat_id) values(#{order_id},#{seat_id}) ")
    void insertOrderDetail(String order_id, Integer seat_id);

    /**
     * 根据订单号查询已失效订单
     * @param order_id
     */
    @Select("select order_id,session_id,total,order_status,user_id from orders where order_id=#{order_id} and order_status='5'")
    Order queryNotPayOrderByOrderId(String order_id);


    /**
     * 用于订单插入后更新订单金额
     * @param order_id
     */
//    @Update("update orders set total=#{price} where order_id=#{order_id}")
//    void updatePriceByOrderId(String order_id,Float price);
    @Update("update orders set total=(select mid.total from (select sum(session.price) + (count(0) * 3) AS total from " +
            "orders inner join order_detail on  orders.order_id=#{order_id} and orders.order_id = order_detail.order_id " +
            "inner join session on orders.session_id = session.session_id " +
            ") mid) where orders.order_id=#{order_id}")
    void updatePriceByOrderId(String order_id);
}
