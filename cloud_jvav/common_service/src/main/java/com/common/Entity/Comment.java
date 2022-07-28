package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Comment  implements Serializable {
    @Id
    private int user_id;
    private String  film_comment;
    private int film_id;
    private Date    comment_date;
    private float   film_grade;
}
