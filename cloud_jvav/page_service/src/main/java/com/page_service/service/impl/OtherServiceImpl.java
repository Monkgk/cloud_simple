package com.page_service.service.impl;

import com.feign.clients.FilmClient;
import com.page_service.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OtherServiceImpl implements OtherService {
    @Autowired
    FilmClient filmClient;

    @Override
    public Boolean insertComment(Integer user_id, Integer film_id, String film_comment, Float film_grade) {
        return filmClient.putComment(user_id,film_id,film_comment,film_grade);
    }
}
