package com.film_service.service;

import com.common.Vo.CommentVo;

import java.util.List;

public interface CommentService {

    /**
     * 插入评论
     * @param user_id
     * @param film_id
     * @param film_comment
     * @param film_grade
     * @return
     */
    Boolean insertComment(Integer user_id,Integer film_id,String film_comment,Float film_grade);

    /**
     * 根据电影Id获取电影的评论
     * @param film_id
     * @return
     */
    List<CommentVo> getFilmComment(Integer film_id);
}
