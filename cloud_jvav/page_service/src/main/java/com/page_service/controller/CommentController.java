package com.page_service.controller;

import com.page_service.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    OtherService    otherService;

    /**
     * 返回写评论页面
     * @return
     */
    @RequestMapping("/{film_id}")
    public String   Comment(){
        return "star_rating";
    }

    @RequestMapping("/{film_id}/toComment")
    @Transactional(propagation = Propagation.REQUIRED)
    public String toComment(@PathVariable("film_id")    Integer film_id,
                            @RequestParam("loginId")    Integer user_id,
                            @RequestParam("grade")      Float   film_grade,
                            @RequestParam("comment")    String  comment) {
        otherService.insertComment(user_id, film_id, comment, film_grade);
        return "redirect:/index/detail/" + film_id;
    }
}
