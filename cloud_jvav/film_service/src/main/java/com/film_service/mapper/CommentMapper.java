package com.film_service.mapper;

import com.common.Entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    //根据电影查找评论,展示十条
    @Select("select comment.user_id,film_comment,film_id,comment_date,film_grade " +
            "from comment " +
            "where film_id=#{film_id} " +
            "order by comment_date " +
            "limit 10")
    List<Comment>   queryByFilmId(Integer film_id);

    //插入评论
    @Insert("insert into comment(user_id,film_id,film_comment,film_grade) " +
            "values(#{user_id},#{film_id},#{film_comment},#{film_grade})")
    Integer insertComment(Integer user_id,Integer film_id,String film_comment,Float film_grade);
}
