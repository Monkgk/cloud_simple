package com.common.Entity;

import cn.hutool.db.DaoTemplate;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Actor implements Serializable {
    @Id
    private int actor_id;
    private String  actor_name;
    private String  actor_picture;
    private String  actor_sex;
    private String  actor_status;
    private Date    create_time;
}
