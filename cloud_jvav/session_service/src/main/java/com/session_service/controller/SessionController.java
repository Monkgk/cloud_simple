package com.session_service.controller;

import com.common.Entity.Session;
import com.session_service.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService  sessionService;

    /**
     * 根据场次编号获取场次信息
     * @param session_id
     * @return
     */
    @RequestMapping("/get/{session_id}")
    Session getSessionBySessionId(@PathVariable("session_id") Integer session_id){
        return sessionService.getSessionInfo(session_id);
    }

    @RequestMapping("/film/{film_id}")
    List<Session>   getSessionsByFilmId(@PathVariable("film_id") int film_id){
        return sessionService.getSessionByFilmId(film_id);
    }

    @RequestMapping("/date/film/{film_id}")
    List<Date>  getSessionDateByFilmId(@PathVariable("film_id") int film_id){
        return sessionService.getSessionDateByFilmId(film_id);
    }

    @RequestMapping("/cinema/{cinema_id}/film/{film_id}")
    TreeMap<String, List<Session>> getSessionsGroupByDate(@PathVariable("cinema_id") int cinema_id,
                                                          @PathVariable("film_id") int film_id){
        return sessionService.getSessionGroupByDate(cinema_id,film_id);
    }
}
