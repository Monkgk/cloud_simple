package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class ActorFilm  implements Serializable {
    @Id
    private int actor_id;
    private String  actor_name;
    private int film_id;
    private String  position = "暂无";
    private String  actor_picture;
}
