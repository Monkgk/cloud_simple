package com.common.Vo;


import com.common.Entity.Comment;
import com.common.Entity.User;
import lombok.Data;

@Data
public class CommentVo {
    private Comment comment;
    private User user;
}
