package com.film_service.service.impl;


import com.common.Entity.Comment;
import com.common.Entity.User;
import com.common.Vo.CommentVo;
import com.feign.clients.UserClient;
import com.film_service.mapper.CommentMapper;
import com.film_service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserClient userClient;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insertComment(Integer user_id, Integer film_id, String film_comment, Float film_grade) {
        if (commentMapper.insertComment(user_id,film_id,film_comment,film_grade)>0){
            return true;
        }
        return false;
    }

    /**
     * 获取相应电影的评论
     * @param film_id
     * @return
     */
    @Override
    public List<CommentVo> getFilmComment(Integer film_id) {
        List<CommentVo> commentVoList   =   new ArrayList<>();
        //查询相关评论
        List<Comment>   comments        =   commentMapper.queryByFilmId(film_id);
        for (Comment comment : comments){
            //创建单条信息对象
            CommentVo   commentVo   =   new CommentVo();
            //根据user_id查询评论用户信息
            User user    =   userClient.getOtherByUserId(comment.getUser_id());
            //存放评论信息
            commentVo.setComment(comment);
            //存放评论用户信息
            commentVo.setUser(user);
            //将评论对象放入返回对象
            commentVoList.add(commentVo);
        }
        return commentVoList;
    }
}
