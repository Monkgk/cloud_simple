package com.film_service.controller;

import com.common.Entity.Film;
import com.film_service.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    /**
     * 返回全部电影
     * @return
     */
    @PostMapping({"/all"})
    public List<Film> getFilms(){
        return filmService.getFilms();
    }

    /**
     * 返回全部电影,按照日期分组
     * @return
     */
    @PostMapping({"/all/groupdata"})
    public List getFilmsGroupData(){
        return filmService.getFilmsGroupData();
    }

    /**
     * 返回电影搜索结果
     * @param string_like
     * @return
     */
    @PostMapping("/likeSerch/{string_like}")
    public List<Film> getFilmByLike(@PathVariable("string_like") String string_like){
        return filmService.getFilmsByLike(string_like);
    }

    @PostMapping("/detail/{filmId}")
    public Film getFilmDetailByFilmId(@PathVariable("filmId") int filmId){
        return filmService.getFilmDetail(filmId);
    }
    
    @PostMapping("/cinema/{cinema_id}/get")
    public List<Film>   getFilmsByCinemaId(@PathVariable("cinema_id") int cinema_id){
        return filmService.getFilmsByCinemaId(cinema_id);
    }
}
