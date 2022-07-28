package com.session_service.controller;

import com.session_service.service.SeatSelectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    SeatSelectedService seatSelectedService;

    /**
     * 返回已被选择的座位列表，格式   “行_列”
     * @param session_id
     * @return
     */
    @RequestMapping("/selected/{session_id}")
    List<String>    getSelectedBySessionId(@PathVariable("session_id") int session_id) {
        return seatSelectedService.getSelectedBySessionId(session_id);
    }
}
