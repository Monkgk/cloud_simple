package com.feign.clients;

import com.common.Entity.ActorFilm;
import com.common.Entity.Film;
import com.common.Entity.Stills;
import com.common.Vo.CommentVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("filmservice")
public interface FilmClient {

    /**
     * 获取电影列表
     * @return
     */
    @PostMapping("/film/all")
    List<Film> getAllFilm();

    /**
     * 获取电影列表,根据日期分组
     * @return
     */
    @PostMapping("/film/all/groupData")
    List getAllFilmGroupByData();

    /**
     * 搜索电影
     * @param string_like
     * @return
     */
    @PostMapping("/film/likeSerch/{string_like}")
    List<Film> getFilmsByLike(@PathVariable("string_like") String string_like);

    /**
     * 根据电影Id获取电影信息
     * @param film_id
     * @return
     */
    @PostMapping("/film/detail/{film_id}")
    Film getDetailByFilmId(@PathVariable("film_id") int film_id);

    /**
     * 根据电影Id获取电影剧照
     * @param film_id
     * @return
     */
    @PostMapping("/film/still/{film_id}")
    List<Stills>    getStillByFilmId(@PathVariable("film_id") int film_id);

    /**
     * 根据电影Id获取电影评论
     * @param film_id
     * @return
     */
    @PostMapping("/film/comment/{film_id}")
    List<CommentVo>    getFilmComment(@PathVariable("film_id") int film_id);

    /**
     * 根据电影Id获取电影演员
     * @param film_id
     * @return
     */
    @PostMapping("/film/actor/{film_id}")
    List<ActorFilm>    getFilmActor(@PathVariable("film_id") int film_id);

    /**
     * 提交评论信息
     * @param user_id
     * @param film_id
     * @param film_comment
     * @param film_grade
     * @return
     */
    @PostMapping("/film/comment/{film_id}/toComment")
    Boolean   putComment(@RequestParam("user_id") Integer user_id,
                         @PathVariable("film_id") Integer film_id,
                         @RequestParam("film_comment") String film_comment,
                         @RequestParam("film_grade") Float film_grade);

    /**
     * 根据影院Id获取电影列表
     * @param cinema_id
     * @return
     */
    @PostMapping("/film/cinema/{cinema_id}/get")
    List<Film> getFilmsByCinemaId(@PathVariable("cinema_id") int cinema_id);
}
