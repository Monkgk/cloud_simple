package com.page_service.controller;

import com.common.Entity.Film;
import com.page_service.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/film")
public class FilmController {
    @Autowired
    FilmService filmService;

    @RequestMapping("/search")
    public String getSearchAll(Model model){
        List<Film> films = filmService.getAllFilm();
        model.addAttribute("films",films);
        return "search_film_iframe";
    }

    @RequestMapping("/search/{like_string}")
    public String getSearchByLike(@PathVariable("like_string") String like_string,
                                      Model model){
        List<Film> films = filmService.getFilmsByLike(like_string);
        model.addAttribute("films",films);
        return "search_film_iframe";
    }

}
