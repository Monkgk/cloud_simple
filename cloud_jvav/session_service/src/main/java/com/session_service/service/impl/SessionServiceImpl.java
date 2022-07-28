package com.session_service.service.impl;

import com.common.Entity.Session;
import com.feign.clients.CinemaClient;
import com.feign.clients.FilmClient;
import com.session_service.mapper.SessionMapper;
import com.session_service.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionMapper sessionMapper;
    @Autowired
    CinemaClient    cinemaClient;
    @Autowired
    FilmClient  filmClient;;

    @Override
    //返回按日期分类的电影场次
    public TreeMap<String, List<Session>> getSessionGroupByDate(Integer cinema_id,Integer film_id){
        //获取场次列表
        List<Session>   sessions    =   sessionMapper.querySessionByCinemaId(cinema_id,film_id);
        //获取日期列表
        List<Date>      dates       =   sessionMapper.querySessionDateByCinemaId(cinema_id,film_id);
        TreeMap<String,List<Session>> datas = new TreeMap<>();
        for (Date i : dates){
            SimpleDateFormat formatDate = new SimpleDateFormat("MM月dd日");
            String date =   formatDate.format(i);
            for (Session j : sessions){
                String  sessionDate =   formatDate.format(j.getStart_time());
                if (sessionDate.equals(date)){
                    if (datas.containsKey(date)){
                        datas.get(date).add(j);
                    }else{
                        List a = new ArrayList<Session>();
                        a.add(j);
                        datas.put(date,a);
                    }
                }
            }
        }
        return datas;
    }

    @Override
    /**
     * 含影院信息、电影信息、放映厅信息
     */
    public Session getSessionInfo(Integer session_id) {
        //根据场次编号查询信息
        Session session = sessionMapper.queryBySessionId(session_id);

        Integer cinema_id   = session.getCinema_id();
        Integer film_id     = session.getFilm_id();

        //添加影院信息
        session.setCinema(cinemaClient.getCinemaByCinemaId(cinema_id));
        //添加电影信息
        session.setFilm(filmClient.getDetailByFilmId(film_id));
        //添加放映厅信息
        session.setRoom(cinemaClient.getRoomByRoomId(session.getRoom_id()));
        return session;
    }

    @Override
    public List<Session> getSessionByFilmId(int film_id) {
        return sessionMapper.queryByFilmId(film_id);
    }

    @Override
    public List<Date> getSessionDateByFilmId(int film_id) {
        return sessionMapper.queryDateByFilmId(film_id);
    }
}
