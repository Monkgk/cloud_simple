package com.film_service.service;

import com.common.Entity.ActorFilm;

import java.util.List;

public interface ActorFilmService {
    /**
     * 根据电影Id获取演员列表
     * @param film_id
     * @return
     */
    List<ActorFilm> getActorByFilmId(int film_id);
}
