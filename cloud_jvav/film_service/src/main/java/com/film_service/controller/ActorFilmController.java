package com.film_service.controller;

import com.common.Entity.ActorFilm;
import com.film_service.service.ActorFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/film")
public class ActorFilmController {
    @Autowired
    ActorFilmService actorFilmService;

    @RequestMapping("/actor/{film_id}")
    public List<ActorFilm> getActorByFilmId(@PathVariable("film_id") int film_id){
        return  actorFilmService.getActorByFilmId(film_id);
    }
}
