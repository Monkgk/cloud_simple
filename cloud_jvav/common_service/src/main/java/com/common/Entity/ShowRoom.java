package com.common.Entity;

import cn.hutool.db.DaoTemplate;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class ShowRoom  implements Serializable {
    @Id
    private int     room_id;
    private String  room_name;
    private int     cinema_id;
    private String  cinema_name;
    private String  room_status;
    private Date    create_time;
    private Date    update_time;
}
