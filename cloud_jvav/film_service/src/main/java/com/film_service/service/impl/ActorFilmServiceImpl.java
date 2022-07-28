package com.film_service.service.impl;

import com.common.Entity.ActorFilm;
import com.film_service.mapper.ActorFilmMapper;
import com.film_service.service.ActorFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorFilmServiceImpl implements ActorFilmService {
    @Autowired
    ActorFilmMapper actorFilmMapper;

    @Override
    public List<ActorFilm> getActorByFilmId(int film_id) {
        return actorFilmMapper.queryByFilmId(film_id);
    }
}
