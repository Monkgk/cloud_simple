package com.session_service.service.impl;

import com.session_service.mapper.SeatMapper;
import com.session_service.mapper.SeatSelectedMapper;
import com.session_service.service.SeatSelectedService;
import com.common.Entity.Seat;
import com.common.Entity.SeatSelected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatSelectedServiceImpl implements SeatSelectedService {
    @Autowired
    private SeatSelectedMapper seatSelectedMapper;
    @Autowired
    private SeatMapper seatMapper;

    //返回已被选座位的字符串信息
    @Override
    public List<String> getSelectedBySessionId(Integer session_id) {
        List<String> seatList = new ArrayList<>();
        //在座位列表中添加座位编号
        for (SeatSelected seat : seatSelectedMapper.queryBySessionId(session_id)){
            //根据座位编号查询座位行和列
            Seat seat1 = seatMapper.queryBySeatId(seat.getSeat_id());
            //前端需要接收字符串，在这里拼接并返回
            String  seat_str  = seat1.getSeat_row()+"_"+seat1.getSeat_column();
            seatList.add(seat_str);
        }
        return seatList;
    }

    /**
     * 根据场次号和座位号删除指定的锁定座位
     * @param session_id
     * @param seatList
     */
    @Override
    public void delectSeatSelected(Integer session_id, List<Seat> seatList) {
        List<Integer> SeatIdList = new ArrayList<>();
        for (Seat seat:seatList){
            SeatIdList.add(seat.getSeat_id());
        }
        seatSelectedMapper.delectSeatSelected(session_id,seatList);
    }
}
