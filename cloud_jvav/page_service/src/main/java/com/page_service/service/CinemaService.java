package com.page_service.service;

import com.common.Entity.Cinema;

import java.util.List;

public interface CinemaService {

    /**
     * 根据影院Id获取影院信息
     * @param cinema_id
     * @return
     */
    Cinema  getCinemaById(int cinema_id);

    /**
     * 获取全部影院
     * @return
     */
    List<Cinema> getCinemaAll();

    /**
     * 根据关键字获取影院
     * @param search_like
     * @return
     */
    List<Cinema> getCinemaByName(String search_like);

}
