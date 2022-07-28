package com.page_service.controller;

import com.common.Entity.Film;
import com.page_service.service.FilmService;
import com.page_service.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    FilmService filmService;

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
}
