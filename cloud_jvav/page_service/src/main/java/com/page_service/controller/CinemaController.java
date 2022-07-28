package com.page_service.controller;

import com.common.Entity.*;
import com.common.Vo.CommentVo;
import com.page_service.service.CinemaService;
import com.page_service.service.FilmService;
import com.page_service.service.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.TreeMap;

@Controller
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    CinemaService   cinemaService;
    @Autowired
    FilmService     filmService;
    @Autowired
    SessionsService sessionsService;

    @RequestMapping()
    public String Cinema(){
        return "cinema";
    }

    @RequestMapping("/search")
    public String CinemaSearchAll(Model model){
        //获取影院的信息列表
        List<Cinema> cinemas = cinemaService.getCinemaAll();
        model.addAttribute("cinemas",cinemas);
        return "search_cinema_iframe";
    }

    @RequestMapping("/search/{like_string}")
    public String CinemaSearch(Model model,
                               @PathVariable("like_string") String like_string){
        //获取影院的信息列表
        List<Cinema> cinemas = cinemaService.getCinemaByName(like_string);
        model.addAttribute("cinemas",cinemas);

        return "search_cinema_iframe";
    }

    @RequestMapping("/{cinema_id}")
    public String CinemaFilm(@PathVariable("cinema_id") int cinema_id, Model model){
        //根据影院编号查找相关电影信息
        List<Film> filmAll = filmService.getFilmsByCinemaId(cinema_id);
        model.addAttribute("films",filmAll);
        return "cinema_film";
    }

    @RequestMapping("/{cinema_id}/film/{film_id}")
    public String ChooseFilm(@PathVariable("cinema_id") int cinema_id,
                             @PathVariable("film_id") int film_id,
                             Model model){
        //获取影院信息
        Cinema cinema  =   cinemaService.getCinemaById(cinema_id);
        //根据影院编号查找影院在播电影
        List<Film> films   =   filmService.getFilmsByCinemaId(cinema_id,film_id);
        model.addAttribute("cinema",cinema);
        model.addAttribute("films",films);
        return "choose_film";
    }

    /**
     * 跳转Detail页面
     * @param film_id
     * @param model
     * @return
     */
    @RequestMapping("/{cinema_id}/{film_id}")
    public String toDetail(@PathVariable("film_id") Integer film_id, Model model){
        //查询电影详细信息
        Film            details     =   filmService.getDetailByFilmId(film_id);
        //查询电影评论
        List<CommentVo> comments    =   filmService.getCommentByFilmId(film_id);
        //查询电影演员信息
        List<ActorFilm> actors      =   filmService.getFilmActor(film_id);
        //查询相关电影剧照
        List<Stills>    stills      =   filmService.getStills(film_id);
        //用于辨识进入电影详情页的入口
        boolean flag = true;
        model.addAttribute("detailData",details);
        model.addAttribute("comments",comments);
        model.addAttribute("ators",actors);
        model.addAttribute("stills",stills);
        model.addAttribute("flag",flag);
        return "film_detail";
    }

    @RequestMapping("/{cinema_id}/{film_id}/get")
    public String getSession(@PathVariable("cinema_id") Integer cinema_id,
                             @PathVariable("film_id") Integer film_id,
                             Model model){
        //获取电影详细信息
        Film    filmdetail  =   filmService.getDetailByFilmId(film_id);
        //获取按日期分类的场次信息
        TreeMap<String,List<Session>> datas = sessionsService.getSessionDate(cinema_id,film_id);
        model.addAttribute("filmdetail",filmdetail);
        model.addAttribute("datas",datas);
        model.addAttribute("film_id",film_id);

        return "choose_session";
    }

    /**
     * 返回影片的场次信息
     * @param film_id
     * @param cinema_id
     * @param model
     * @return
     */
    @RequestMapping("/{cinema_id}/film/{film_id}/session")
    public String ChooseSession(@PathVariable("cinema_id")  Integer cinema_id,
                                @PathVariable("film_id")    Integer film_id,
                                Model  model){
        //获取电影详细信息
        Film    filmdetail  =   filmService.getDetailByFilmId(film_id);
        //获取按日期分类的场次信息
        TreeMap<String,List<Session>> datas = sessionsService.getSessionDate(cinema_id,film_id);
        model.addAttribute("filmdetail",filmdetail);
        model.addAttribute("datas",datas);
        model.addAttribute("film_id",film_id);
        return "film_session1";
    }


    @RequestMapping("/{cinema_id}/{film_id}/session/{session_id}")
    public String ChooseSeat(@PathVariable("cinema_id") int cinema_id,
                             @PathVariable("film_id") int film_id,
                             @PathVariable("session_id") int session_id,
                             Model  model){
        //根据场次编号查询场次的所有信息
        Session     session =   sessionsService.getSessionInfo(session_id);
        //根据场次编号查询已被购买的座位
        List<String> seatSelecteds = sessionsService.getSelectedBySessionId(session_id);
        model.addAttribute("sessionInfo",session);
        model.addAttribute("seatSelecteds",seatSelecteds);

        return "seat_selection";
    }


    @RequestMapping("/film/search")
    public String getSearchAll(Model model){
        List<Film> films = filmService.getAllFilm();
        model.addAttribute("films",films);
        return "cinema_search_film_iframe";
    }

    @RequestMapping("/film/search/{like_string}")
    public String getSearchByLike(@PathVariable("like_string") String like_string,
                                  Model model){
        List<Film> films = filmService.getFilmsByLike(like_string);
        model.addAttribute("films",films);
        return "cinema_search_film_iframe";
    }
}
