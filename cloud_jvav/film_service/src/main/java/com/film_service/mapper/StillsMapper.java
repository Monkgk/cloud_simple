package com.film_service.mapper;

import com.common.Entity.Stills;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StillsMapper {
    /**
     * 根据电影编号film_id查找电影的所有剧照,返回可能不止一条数据
     */
    @Select("select still_url from stills where film_id=#{film_id}")
    List<Stills>    queryStillsByFilmId(Integer film_id);
}
