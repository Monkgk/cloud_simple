package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Cinema  implements Serializable {
    @Id
    private int     cinema_id;
//    private int     parent_id;
//    private String  ancestors;
    private String  cinema_name;
    private String  cinema_addr;
    private float   price;
    private String  cinema_status;
    private Date    create_time;
    private Date    update_time;
}
