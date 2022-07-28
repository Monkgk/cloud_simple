package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Seat  implements Serializable {
    @Id
    private int seat_id;
    private int seat_row;
    private int seat_column;
}
