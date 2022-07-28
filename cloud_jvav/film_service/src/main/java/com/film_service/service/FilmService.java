package com.film_service.service;


import com.common.Entity.Film;

import java.util.List;

public interface FilmService {
    /**
     * 根据影院编号查找在播电影
     * @param cinema_id
     * @return
     */
    List<Film> getFilmsByCinemaId(Integer cinema_id);

    /**
     * 根据给定字符串对电影名进行模糊查询
     * @param like_string
     * @return
     */
    List<Film> getFilmsByLike(String like_string);

    /**
     * 查询所有电影,根据日期分类
     * @return
     */
    List getFilmsGroupData();

    /**
     * 获取所有电影
     * @return
     */
    List<Film> getFilms();

    /**
     * 获取电影细节
     * @param film_id
     * @return
     */
    Film getFilmDetail(int film_id);
}
