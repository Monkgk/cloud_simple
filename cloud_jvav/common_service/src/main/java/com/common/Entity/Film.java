package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Film  implements Serializable {
    @Id
    private int film_id;
    private String  film_name;
    private String  film_USname;
    private String  film_region;
    private String  film_desc;
    private String  film_pic;
    private String  actor_toghter;
    private Date    film_show_time;
    private int film_cinema_number;
    private int film_session_number;
    private int film_want;
    private String  introduction;
    private int film_duration;
    private String film_status;
    private Date update_time;
}
