package com.feign.clients;

import com.common.Entity.Session;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

@FeignClient("sessionservice")
public interface SessionClient {

    /**
     * 根据场次Id获取场次信息
     * @return
     */
    @RequestMapping("/session/get/{session_id}")
    Session getSessionById(@PathVariable("session_id") Integer session_id);

    @PostMapping("/session/film/{film_id}")
    List<Session> getSessionByFilmId(@PathVariable("film_id") int film_id);

    @PostMapping("/session/date/film/{film_id}")
    List<Date> getSessionDateByFilmId(@PathVariable("film_id") int film_id);

    /**
     * 根据影院id和电影Id获取按日期分类的场次信息
     * @param cinema_id
     * @param film_id
     * @return
     */
    @PostMapping("/session/cinema/{cinema_id}/film/{film_id}")
    TreeMap<String, List<Session>> getSessionsGroupByDate(@PathVariable("cinema_id") int cinema_id,
                                                          @PathVariable("film_id") int film_id);

    /**
     * 根据场次Id获取已被选择的座位
     * @param session_id
     * @return
     */
    @PostMapping("/seat/selected/{session_id}")
    List<String>    getSelectedBySessionId(@PathVariable("session_id") int session_id);
}
