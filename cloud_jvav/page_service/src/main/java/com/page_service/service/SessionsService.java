package com.page_service.service;

import com.common.Entity.Session;

import java.util.List;
import java.util.TreeMap;

public interface SessionsService {
    /**
     * 根据影院id和电影Id获取按日期分类的场次信息
     * @param cinema_id
     * @param film_id
     * @return
     */
    TreeMap<String, List<Session>> getSessionDate(int cinema_id, int film_id);

    /**
     * 根据场次ID获取场次信息
     * @param session_id
     * @return
     */
    Session getSessionInfo(int session_id);

    /**
     * 获取被选择的座位
     * @param session_id
     * @return
     */
    List<String> getSelectedBySessionId(int session_id);
}
