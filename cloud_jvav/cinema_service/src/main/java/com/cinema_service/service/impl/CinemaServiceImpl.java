package com.cinema_service.service.impl;


import com.cinema_service.mapper.CinemaMapper;
import com.cinema_service.service.CinemaService;
import com.common.Entity.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    CinemaMapper cinemaMapper;

    /**
     * 返回影院信息
     * @param cinema_id
     * @return
     */
    @Override
    public Cinema getCinemaById(Integer cinema_id) {
        return cinemaMapper.queryByCinemaId(cinema_id);
    }

    /**
     * 返回影院列表
     */
    @Cacheable(value = "cinemaAll",key = "#root.methodName+'_cinemaAll'")
    @Override
    public List<Cinema> getCinemaAll() {
        //获取影院的信息
        List<Cinema> cinemas = cinemaMapper.queryByCinema();
        return cinemas;
    }

    @Override
    public List<Cinema> getCinemaByName(String search_like) {
        return cinemaMapper.queryByLikeName(search_like);
    }
}
