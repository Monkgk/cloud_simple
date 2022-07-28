package com.order_service.service.impl;

import com.common.Entity.Order;
import com.common.Entity.Seat;
import com.common.Util.SessionUtils;
import com.common.Util.UUIDUtils;
import com.common.Vo.OrderStatus;
import com.common.exception.JvavException;
import com.feign.clients.SessionClient;
import com.order_service.mapper.OrderMapper;
import com.order_service.mapper.SeatMapper;
import com.order_service.mapper.SeatSelectedMapper;
import com.order_service.service.OrderService;
import com.order_service.service.SeatSelectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    SeatMapper  seatMapper;
    @Autowired
    SeatSelectedMapper  seatSelectedMapper;
    @Autowired
    SessionClient   sessionClient;

    @Override
    public List<Order> getOrdersByUserId(Integer user_id) throws JvavException {
        List<Order> orders  =   orderMapper.queryByUserId(user_id);
        for (Order order:orders) {
            //获取订单的座位号
            order.setSeatList(seatMapper.querySeatsByOrderId(order.getOrder_id()));
            //获取电影场次详细信息
            order.setSessionInfo(sessionClient.getSessionById(order.getSession_id()));
            //获取订单状态
            order.setOrder_status_msg(OrderStatus.getMsg(order.getOrder_status()).getMsg());
        }
        return orders;
    }

    @Override
    public Order queryNotPayOrderByUserId(int user_id) throws JvavException{
        Order order  =  orderMapper.queryNotPayOrderByUserId(user_id);
        //存在未支付订单
        if (order!=null){
            //获取订单金额并存入对象
            Float   total   =   orderMapper.queryPriceByOrderId(order.getOrder_id());
            order.setTotal(total);
            Date current_date   =   new Date();
            //过期时间
            Long invalid_time   =   order.getCreate_time().getTime()+1000*60*15;
            //剩余时间
            Long surplus_time = (invalid_time-current_date.getTime())/1000;
            if (surplus_time>0){
                order.setSurplus_time(surplus_time);
            }else{
                //订单已过期
                throw new JvavException("订单已过期");
            }
        }
        return order;
    }

    /**
     * 向数据库中添加订单,成功返回订单号，失败抛出异常
     * @param selected
     * @param sessionId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public String addOrder(HashMap<String, List<Integer>> selected, Integer sessionId,Integer user_id) throws JvavException {
        //获取当前用户ID
//        Integer user_id = SessionUtils.getCurrentUser().getUser_id();
        //创建一个用于存放订单信息的Order对象
        Order order = new Order();
        //存放座位信息
        List<Seat>  seats    =   new ArrayList<>();
        //为同个订单的多个座位设置相同信息
        //订单编号
        order.setOrder_id(UUIDUtils.randomUUID());
        //场次编号
        order.setSession_id(sessionId);
        //用户ID
        order.setUser_id(user_id);
        //订单金额，后面根据信息修改，先设置为999.99
        order.setTotal(999.99f);
        //订单创建时间,数据库插入时创建

        //订单状态，初次创建时设置为未支付
        order.setOrder_status(OrderStatus.ORDER_STAYPAY.getCode());
        //向数据库插入订单
        orderMapper.insertInOrder(order);

        //插入订单的其他信息，如座位信息
        //遍历座位，并添加相应内容
        for (String key:selected.keySet()){
            List<Integer> seat = selected.get(key);
            //根据座位信息查找座位详细信息
            Seat seatInfo    =   seatMapper.queryBySeat(seat.get(0), seat.get(1));
            //判断该座位是否已被选择，已被选择则抛出异常进行回滚
            if (!seatSelectedMapper.queryBySessionIdAndSeatId(sessionId,seatInfo.getSeat_id()).isEmpty()){
                //抛出异常，此处代码结束
                throw new JvavException("座位已被选择");
            }else {
                //锁定座位，向数据库中插入选择的座位信息
                seatSelectedMapper.insertInSeatSelected(sessionId,seatInfo.getSeat_id());
                //插入订单与座位的中间表信息
                orderMapper.insertOrderDetail(order.getOrder_id(),seatInfo.getSeat_id());

                seats.add(seatInfo);
            }
        }
        //座位信息存入订单
        order.setSeatList(seats);
        //更新订单金额
        orderMapper.updatePriceByOrderId(order.getOrder_id());

        seats.clear();
        return order.getOrder_id();
    }

    @Override
    public Order getOrderByOrderId(String order_id) {
        return orderMapper.queryOrderByOrderId(order_id);
    }

    @Override
    public void setOrderStatuById(String orderId, String status) {
        orderMapper.updateOrderStatusByOrderId(orderId,status);
    }

    @Override
    public void delectSeatSelectedByOrderId(String order_id) {
        Order InvalidOrder = orderMapper.queryOrderByOrderId(order_id);
        //放入座位列表
        List<Seat> seatList = seatMapper.querySeatsByOrderId(order_id);
        InvalidOrder.setSeatList(seatList);
        if (InvalidOrder!=null){
            seatSelectedMapper.delectSeatSelected(InvalidOrder.getSession_id(),InvalidOrder.getSeatList());
        }
    }
}
