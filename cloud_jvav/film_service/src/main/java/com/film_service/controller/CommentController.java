package com.film_service.controller;

import com.common.Vo.CommentVo;
import com.film_service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/film")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping("/comment/{film_id}")
    public List<CommentVo> getCommentByFilmId(@PathVariable("film_id") int film_id){
        return commentService.getFilmComment(film_id);
    }

    //提交评论
    @RequestMapping("/comment/{film_id}/toComment")
    @Transactional(propagation = Propagation.REQUIRED)
    public void toComment(@PathVariable("film_id")    Integer film_id,
                            @RequestParam("user_id")    Integer user_id,
                            @RequestParam("film_grade")      Float   film_grade,
                            @RequestParam("film_comment")    String  comment){
        commentService.insertComment(user_id,film_id,comment,film_grade);
//        return "redirect:/home/detail?id="+film_id;
    }
}
