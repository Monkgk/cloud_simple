package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class User  implements Serializable {
    @Id
    private Integer user_id;
    private String  user_name;
    private String  user_pwd;
    private String  user_head="default_head.png";//用户头像URL地址
    private int user_sex;//1为男，0为女
    private String  user_phone;
}
