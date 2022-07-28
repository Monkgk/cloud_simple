package com.page_service.service.impl;

import com.common.Entity.*;
import com.common.Vo.CommentVo;
import com.feign.clients.CinemaClient;
import com.feign.clients.FilmClient;
import com.feign.clients.SessionClient;
import com.page_service.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmClient  filmClient;
    @Autowired
    CinemaClient    cinemaClient;
    @Autowired
    SessionClient   sessionClient;

    @Override
    public List<Film> getAllFilm() {
        return filmClient.getAllFilm();
    }

    /**
     * 获取全部电影,根据日期分类
     * @return
     */
    public List  getAllFilmGroupByData(){
        return filmClient.getAllFilmGroupByData();
    }

    @Override
    public List<Film> getFilmsByLike(String like_string) {
        return filmClient.getFilmsByLike(like_string);
    }

    @Override
    public Film getDetailByFilmId(int film_id) {
        return filmClient.getDetailByFilmId(film_id);
    }

    @Override
    public List<Stills> getStills(int film_id) {
        return filmClient.getStillByFilmId(film_id);
    }

    @Override
    public List<CommentVo> getCommentByFilmId(int film_id) {
        return filmClient.getFilmComment(film_id);
    }

    @Override
    public List<ActorFilm> getFilmActor(int film_id) {
        return filmClient.getFilmActor(film_id);
    }

    @Override
    public TreeMap<String,List<Cinema>> getDayCinema(int film_id) {
        //获取所有影院信息
        List<Cinema>    cinemas     =   cinemaClient.getCinemaAll();
        //获取相关电影的场次和影院信息
        List<Session>   sessions    =   sessionClient.getSessionByFilmId(film_id);
        //获取日期列表
        List<Date>      dates       =   sessionClient.getSessionDateByFilmId(film_id);
        TreeMap<String,List<Cinema>>    datas   =   new TreeMap<>();
        //在session中放入影院信息
        for(Session session:sessions){
            for (Cinema cinema:cinemas){
                if (session.getCinema_id()==cinema.getCinema_id())
                    session.setCinema(cinema);
            }
        }
        //按日期分类放入影院信息
        for (Date i : dates){
            SimpleDateFormat formatDate = new SimpleDateFormat("MM月dd日");
            String date =   formatDate.format(i);
            for (Session session:sessions){
                String  sessionDate =   formatDate.format(session.getStart_time());
                if (sessionDate.equals(date)){
                    if (datas.containsKey(date)){
                        datas.get(date).add(session.getCinema());
                    }else{
                        List a = new ArrayList<Cinema>();
                        a.add(session.getCinema());
                        datas.put(date,a);
                    }
                }

            }
        }

        return datas;
    }

    @Override
    public List<Film> getFilmsByCinemaId(int cinema_id,int film_id) {
        List<Film>  films   =   filmClient.getFilmsByCinemaId(cinema_id);
        //将首选电影排在第一位
        for (int i=0;i<films.size();i++){
            if(films.get(i).getFilm_id()==film_id){
                //不影响原先的相对位置
                films.add(0, films.remove(i));
                break;
            }
        }
        return films;
    }

    @Override
    public List<Film> getFilmsByCinemaId(int cinema_id) {
        return filmClient.getFilmsByCinemaId(cinema_id);
    }
}
