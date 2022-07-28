package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Session implements Serializable {
    @Id
    private int session_id;
    private String room_name;
    private int room_id;
    private String cinema_name;
    private int cinema_id;
    private String film_name;
    private int film_id;
    private String film_type;
    private Date start_time;
    private float price;
    @Transient
    private ShowRoom room;
    @Transient
    private Cinema cinema;
    @Transient
    private Film film;
    private String session_status;
}
