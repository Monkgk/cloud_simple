package com.page_service.service.impl;

import com.common.Entity.Cinema;
import com.feign.clients.CinemaClient;
import com.page_service.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    CinemaClient    cinemaClient;
    @Override
    public Cinema getCinemaById(int cinema_id) {
        return cinemaClient.getCinemaByCinemaId(cinema_id);
    }

    @Override
    public List<Cinema> getCinemaAll() {
        return cinemaClient.getCinemaAll();
    }

    @Override
    public List<Cinema> getCinemaByName(String search_like) {
        return cinemaClient.getCinemaByLike(search_like);
    }
}
