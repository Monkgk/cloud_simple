package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Stills  implements Serializable {
    @Id
    private int film_id;
    private String  still_url;
}
