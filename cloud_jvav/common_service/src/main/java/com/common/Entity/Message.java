package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Message  implements Serializable {
    @Id
    private int msg_id;
    private int msg_type_id;
    private String  order_id;
    private String  msg_title;
    private String  msg_type;
    private String  msg_content;
    private int user_id;
    private Date    create_time;
}
