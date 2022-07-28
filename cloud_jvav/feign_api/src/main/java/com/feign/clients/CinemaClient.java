package com.feign.clients;

import com.common.Entity.Cinema;
import com.common.Entity.ShowRoom;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("cinemaservice")
public interface CinemaClient {

    /**
     * 获取全部影院
     * @return
     */
    @PostMapping("/cinema/all")
    List<Cinema> getCinemaAll();

    /**
     * 根据影院Id获取影院信息
     * @param cinema_id
     * @return
     */
    @PostMapping("/cinema/get/{cinema_id}")
    Cinema  getCinemaByCinemaId(@PathVariable("cinema_id") Integer cinema_id);



    @RequestMapping("/showroom/get/{room_id}")
    ShowRoom getRoomByRoomId(@PathVariable("room_id") Integer room_id);

    /**
     * 根据关键字获取影院列表
     * @param search_like
     * @return
     */
    @PostMapping("/cinema/like/get/{like_string}")
    List<Cinema> getCinemaByLike(@PathVariable("like_string") String search_like);
}
