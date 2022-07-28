package com.film_service.mapper;

import com.common.Entity.ActorFilm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActorFilmMapper {
    //通过电影id查找所有演员信息
    @Select("select distinct actor.actor_id,actor.actor_name,actor.actor_picture,actor_film.position " +
            "from actor_film " +
            "left join actor " +
            "on actor.actor_id=actor_film.actor_id " +
            "where film_id=#{film_id} " +
            "ORDER BY case position WHEN '导演' then position END DESC")
    List<ActorFilm> queryByFilmId(Integer   film_id);

    //通过演员id查找相应电影信息
    @Select("select actor_id,film_id,position form actor_film where actor_id=#{actor_id}")
    List<ActorFilm> queryByActorId(Integer   actor_id);
}
