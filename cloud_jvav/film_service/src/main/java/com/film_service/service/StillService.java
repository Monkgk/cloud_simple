package com.film_service.service;

import com.common.Entity.Stills;

import java.util.List;

public interface StillService {
    /**
     * 通过电影Id获取电影的相关剧照
     * @param film_id
     * @return
     */
    List<Stills>    getStillsByFilmId(int film_id);
}
