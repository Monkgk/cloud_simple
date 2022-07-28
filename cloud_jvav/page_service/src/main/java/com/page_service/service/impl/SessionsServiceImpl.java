package com.page_service.service.impl;

import com.common.Entity.Session;
import com.feign.clients.SessionClient;
import com.page_service.service.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

@Service
public class SessionsServiceImpl implements SessionsService {
    @Autowired
    SessionClient   sessionClient;

    @Override
    public TreeMap<String, List<Session>> getSessionDate(int cinema_id, int film_id) {
        return sessionClient.getSessionsGroupByDate(cinema_id,film_id);
    }

    @Override
    public Session getSessionInfo(int session_id) {
        return sessionClient.getSessionById(session_id);
    }

    @Override
    public List<String> getSelectedBySessionId(int session_id) {
        return sessionClient.getSelectedBySessionId(session_id);
    }
}
