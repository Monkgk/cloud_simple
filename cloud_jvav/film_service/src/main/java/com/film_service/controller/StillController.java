package com.film_service.controller;

import com.common.Entity.Stills;
import com.film_service.service.StillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/film")
public class StillController {
    @Autowired
    StillService    stillService;

    @PostMapping("/still/{film_id}")
    public List<Stills> getStillByFilmId(@PathVariable("film_id") int film_id){
        return  stillService.getStillsByFilmId(film_id);
    }
}
