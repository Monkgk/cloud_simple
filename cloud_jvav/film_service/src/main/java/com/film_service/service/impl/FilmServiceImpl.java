package com.film_service.service.impl;

import com.common.Entity.Film;
import com.film_service.mapper.FilmMapper;
import com.film_service.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmMapper filmMapper;

    /**
     * 根据影院编号查找在播电影
     * @param cinema_id
     * @return
     */
    @Override
    public List<Film> getFilmsByCinemaId(Integer cinema_id) {
        return filmMapper.queryFilmByCinemaId(cinema_id);
    }

    /**
     * 根据给定字符串对电影名进行模糊查询
     * @param like_string
     * @return
     */
    @Override
    public List<Film> getFilmsByLike(String like_string) {
        return filmMapper.queryByLikeName(like_string);
    }

    /**
     * 查询所有电影
     * @return
     */
    @Override
    public List<Film>   getFilms(){
        return filmMapper.queryAll();
    }

    /**
     * 查询所有电影,根据日期分类
     * @return
     */
    @Override
    public List getFilmsGroupData() {
        List<Film>    films    =   filmMapper.queryAll();
        List<Date>    dates    =   filmMapper.findFilmDateInfo();
        Map<Date,List<Film>> dayfilms=new HashMap<>();
        List    datas   =   new ArrayList();
        for(Film i:films){
            Date date = i.getFilm_show_time();
            for(Date j:dates){
                if (j.equals(date)){
                    try {
                        if(dayfilms.containsKey(date)){  //如果集合中包含该日期则向列表添加数据，否则新增键值
                            dayfilms.get(date).add(i);
                        }else {
                            List a = new ArrayList<Film>();
                            a.add(i);
                            dayfilms.put(date,a);
                        }
                    }catch (NullPointerException e){

                    }
                }
            }
        }
        datas.add(films);
        datas.add(dayfilms);
        return datas;
    }

    /**
     * 根据电影Id查找电影详细信息
     * @param film_id
     * @return
     */
    @Override
    public Film getFilmDetail(int film_id) {
        return filmMapper.queryById(film_id);
    }
}
