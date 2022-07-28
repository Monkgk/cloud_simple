package com.film_service.service.impl;

import com.common.Entity.Stills;
import com.film_service.mapper.StillsMapper;
import com.film_service.service.StillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StillServiceImpl implements StillService {
    @Autowired
    StillsMapper stillsMapper;

    @Override
    public List<Stills> getStillsByFilmId(int film_id) {
        return stillsMapper.queryStillsByFilmId(film_id);
    }
}
