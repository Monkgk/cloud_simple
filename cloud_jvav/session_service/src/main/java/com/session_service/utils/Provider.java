package com.session_service.utils;

import com.common.Entity.Seat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public class Provider {
    /* 批量删除 */
    public String batchDelete(@Param("session_id") Integer session_id, @Param("SeatIdList") List<Seat> SeatIdList) {
        StringBuilder str = new StringBuilder();
        str.append("delete seat_selected from seat_selected left join orders on seat_selected.session_id=orders.session_id where orders.order_status=5 and seat_selected.session_id=");
        str.append(session_id+" and seat_selected.seat_id in (");
        for (int i=0;i<SeatIdList.size();i++){
            str.append(SeatIdList.get(i).getSeat_id());
            if (i<SeatIdList.size()-1){
                str.append(",");
            }
        }
        str.append(")");
        return str.toString();
    }
}
