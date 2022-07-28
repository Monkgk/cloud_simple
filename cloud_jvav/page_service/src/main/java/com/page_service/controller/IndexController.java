package com.page_service.controller;

import com.common.Entity.ActorFilm;
import com.common.Entity.Cinema;
import com.common.Entity.Film;
import com.common.Entity.Stills;
import com.common.Vo.CommentVo;
import com.page_service.service.FilmService;
import com.page_service.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.TreeMap;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    FilmService filmService;
    @Autowired
    OtherService otherService;

    /**
     * home,首页
     * @param model
     * @return
     */
    @RequestMapping()
    public String Home(Model model){
        List<Film> films = filmService.getAllFilm();
        model.addAttribute("films",films);
        return "home";
    }

    /**
     * 首页搜索电影入口
     * @return
     */
    @RequestMapping("/search")
    public String SearchFilm(){
        return "SearchFilm";
    }

    /**
     * 根据关键字查找电影
     * @return
     */
    @RequestMapping("/tosearch")
    public  String toSearchFilm(){
        return "search_film_iframe";
    }
    @RequestMapping("/tosearch/{string_like}")
    public  String toSearchFilm(@PathVariable("string_like") String search_like,
                                Model model){
        String like_string = search_like;
        if (like_string!=null){
            List<Film> films = filmService.getFilmsByLike(like_string);
            model.addAttribute("films",films);
        }
        return "search_film_iframe";
    }

    @RequestMapping("/detail/{film_id}")
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
        Integer flag = 0;
        model.addAttribute("detailData",details);
        model.addAttribute("comments",comments);
        model.addAttribute("ators",actors);
        model.addAttribute("stills",stills);
        model.addAttribute("flag",flag);
        return "film_detail";
    }

    @RequestMapping("/detail/{film_id}/cinema")
    public String DetailCinema(@PathVariable("film_id")  int film_id,
                               Model model){
        //电影名称
        String          film_name   =   filmService.getDetailByFilmId(film_id).getFilm_name();
        //按日期分类的影院信息
        TreeMap<String,List<Cinema>> dayCinema = filmService.getDayCinema(film_id);

        model.addAttribute("dayCinema",dayCinema);
        model.addAttribute("film_name",film_name);
        model.addAttribute("film_id",film_id);
        return "choose_data";
    }
}
