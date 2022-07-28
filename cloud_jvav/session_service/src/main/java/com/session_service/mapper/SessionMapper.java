package com.session_service.mapper;

import com.common.Entity.Session;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface SessionMapper {
    //通过场次编号session_id查找该场次的信息编号
    @Select("select session_id,room_id,cinema_id,film_id,film_type,start_time,price from session where session_id=#{session_id}")
    Session   queryBySessionId(Integer session_id);

    //根据电影编号查找场次信息
    @Select("select session_id,room_id,session.cinema_id,film_id,film_type,start_time,price " +
            "from session " +
            "left join cinema " +
            "on session.cinema_id=cinema.cinema_id " +
            "where film_id=#{film_id} " +
            "order by start_time")
    List<Session>   queryByFilmId(Integer   film_id);

    //根据电影编号查找日期列表
    @Select("select distinct start_time " +
            "from session " +
            "where film_id=#{film_id} " +
            "order by start_time")
    List<Date>  queryDateByFilmId(Integer film_id);


    //根据影院编号和电影编号查找场次信息
    @Select("select session_id,room_id,cinema_id,film_id,film_type,start_time,price " +
            "from session " +
            "where cinema_id=#{cinema_id} and film_id=#{film_id} " +
            "order by start_time")
    List<Session>   querySessionByCinemaId(Integer cinema_id,Integer film_id);

    //  根据影院编号和电影编号查找场次日期信息
    @Select("select distinct start_time " +
            "from session " +
            "where cinema_id=#{cinema_id} and film_id=#{film_id} " +
            "order by start_time")
    List<Date> querySessionDateByCinemaId(Integer cinema_id, Integer film_id);
}
