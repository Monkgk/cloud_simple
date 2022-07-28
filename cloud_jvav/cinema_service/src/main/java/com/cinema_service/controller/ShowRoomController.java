package com.cinema_service.controller;

import com.cinema_service.service.ShowRoomService;
import com.common.Entity.ShowRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/showroom")
public class ShowRoomController {
    @Autowired
    ShowRoomService showRoomService;

    @RequestMapping("/get/{room_id}")
    public ShowRoom getRoomByRoomId(@PathVariable("room_id") Integer room_id){
        return showRoomService.getRoomById(room_id);
    }

}
