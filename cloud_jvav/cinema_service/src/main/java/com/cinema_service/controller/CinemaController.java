package com.cinema_service.controller;

import com.cinema_service.service.CinemaService;
import com.common.Entity.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    CinemaService   cinemaService;

    /**
     * 获取全部影院信息
     * @return
     */
    @RequestMapping("/all")
    public List<Cinema> getCinemaAll(){
        return cinemaService.getCinemaAll();
    }

    /**
     * 根据影院Id获取影院信息
     * @param cinema_id
     * @return
     */
    @RequestMapping("/get/{cinema_id}")
    public Cinema getCinemaByCinemaId(@PathVariable("cinema_id") Integer cinema_id) {
        return cinemaService.getCinemaById(cinema_id);
    }

    @RequestMapping("/like/get/{like_string}")
    public List<Cinema> getCinemasByLike(@PathVariable("like_string") String like_string){
        return cinemaService.getCinemaByName(like_string);
    }

}
