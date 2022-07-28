package com.cinema_service.service.impl;


import com.cinema_service.mapper.ShowRoomMapper;
import com.cinema_service.service.ShowRoomService;
import com.common.Entity.ShowRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowRoomServiceImpl implements ShowRoomService {
    @Autowired
    ShowRoomMapper showRoomMapper;

    @Override
    public ShowRoom getRoomById(Integer session_id) {
        return showRoomMapper.queryRoomByRoomId(session_id);
    }
}
