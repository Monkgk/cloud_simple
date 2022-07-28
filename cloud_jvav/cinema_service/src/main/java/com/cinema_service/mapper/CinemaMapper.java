package com.cinema_service.mapper;

import com.common.Entity.Cinema;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CinemaMapper {
    //查找所有影院
    @Select("select cinema_id,cinema_name,cinema_addr,min(price) price from choose_cinema group by cinema_id")
    List<Cinema>    queryByCinema();
    //根据影院id查找影院信息
    @Select("select cinema_id,cinema_name,cinema_addr from cinema where cinema_id=#{cinema_id}")
    Cinema  queryByCinemaId(Integer cinema_id);

    /**
     * 根据关键字获取影院
     * @param search_like
     * @return
     */
    @Select("select cinema_id,cinema_name,cinema_addr,min(price) price from choose_cinema group by cinema_id having cinema_name like '%${search_like}%'")
    List<Cinema> queryByLikeName(String search_like);
}
