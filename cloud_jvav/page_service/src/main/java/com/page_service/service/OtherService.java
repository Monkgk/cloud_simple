package com.page_service.service;


public interface OtherService {

    /**
     * 插入评论
     * @param user_id
     * @param film_id
     * @param film_comment
     * @param film_grade
     * @return
     */
    Boolean insertComment(Integer user_id,Integer film_id,String film_comment,Float film_grade);
}
