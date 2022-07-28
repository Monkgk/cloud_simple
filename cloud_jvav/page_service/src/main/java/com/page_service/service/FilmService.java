package com.page_service.service;

import com.common.Entity.ActorFilm;
import com.common.Entity.Cinema;
import com.common.Entity.Film;
import com.common.Entity.Stills;
import com.common.Vo.CommentVo;

import java.util.List;
import java.util.TreeMap;

public interface FilmService {

    /**
     * 获取全部电影
     * @return
     */
    List<Film>  getAllFilm();

    /**
     * 获取全部电影,根据日期分类
     * @return
     */
    List  getAllFilmGroupByData();

    /**
     * 根据给定字符串对电影名进行模糊查询
     * @param like_string
     * @return
     */
    List<Film> getFilmsByLike(String like_string);

    /**
     * 根据电影id获取电影的详细信息
     * @param film_id
     * @return
     */
    Film getDetailByFilmId(int film_id);

    /**
     * 获取电影剧照
     */
    List<Stills>    getStills(int film_id);

    /**
     * 根据电影Id获取电影相关评论
     * @param film_id
     * @return
     */
    List<CommentVo>    getCommentByFilmId(int film_id);

    /**
     * 根据电影Id获取电影相关演员
     * @param film_id
     * @return
     */
    List<ActorFilm>    getFilmActor(int film_id);

    /**
     * 获取影片不同日期播放的影院
     * @param film_id
     * @return
     */
    TreeMap<String,List<Cinema>> getDayCinema(int film_id);

    /**
     * 获取指定影院放映的电影列表,并将指定电影提前
     * @param cinema_id
     * @param film_id
     * @return
     */
    List<Film> getFilmsByCinemaId(int cinema_id,int film_id);

    /**
     * 获取指定影院放映的电影列表
     * @param cinema_id
     * @return
     */
    List<Film> getFilmsByCinemaId(int cinema_id);
}
