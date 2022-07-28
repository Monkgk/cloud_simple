package com.film_service.mapper;


import com.common.Entity.Film;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;


@Mapper
public interface FilmMapper {

    //查找所有电影信息
    @Select("select film.film_id,film_region,film_name,film_desc,film_pic," +
            "film_show_time,actor_toghter,film_cinema_number,film_session_number,film_want " +
            "from film " +
            "left join" +
            "(select GROUP_CONCAT(actor_com.actor_name) actor_toghter,film_id from actor_com group by film_id) actor " +
            "on film.film_id=actor.film_id " +
            "order by film_show_time")
    List<Film>    queryAll();

    //根据电影Id查找电影信息
    @Select("select * from film where film_id=#{film_id}")
    Film    queryById(Integer film_id);
    //获取日期
    @Select("Select film_show_time from film group by film_show_time")
    List<Date> findFilmDateInfo();

    //根据影院编号查找电影信息
    @Select("select distinct film.film_id,film_region,film_name,film_desc,film_pic," +
            "film_show_time,actor_toghter,film_cinema_number,film_session_number,film_want " +
            "from film " +
            "left join" +
            "(select GROUP_CONCAT(actor_com.actor_name) actor_toghter,film_id from actor_com group by film_id) actor " +
            "on film.film_id=actor.film_id " +
            "left join session " +
            "on film.film_id=session.film_id " +
            "where session.cinema_id=#{cinema_id}")
    List<Film>  queryFilmByCinemaId(Integer cinema_id);

    @Select("select film.film_id,film_region,film_name,film_desc,film_pic," +
            "film_show_time,actor_toghter,film_cinema_number,film_session_number,film_want " +
            "from film " +
            "left join" +
            "(select GROUP_CONCAT(actor_com.actor_name) actor_toghter,film_id from actor_com group by film_id) actor " +
            "on film.film_id=actor.film_id " +
            " where film_name like '%${like_string}%' "+
            "order by film_show_time")
    List<Film> queryByLikeName(String like_string);
}
