package com.session_service.service;

import com.common.Entity.Session;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public interface SessionService {
    /**
     *返回按日期分类的电影场次
     */
    public TreeMap<String, List<Session>> getSessionGroupByDate(Integer cinema_id, Integer film_id);
    /**
     * 含影院信息、电影信息、放映厅信息
     */
    Session getSessionInfo(Integer session_id);

    /**
     * 根据电影Id获取场次信息
     * @param film_id
     * @return
     */
    List<Session> getSessionByFilmId(int film_id);

    /**
     * 根据电影Id获取所有场次放映的日期列表
     * @param film_id
     * @return
     */
    List<Date> getSessionDateByFilmId(int film_id);
}
