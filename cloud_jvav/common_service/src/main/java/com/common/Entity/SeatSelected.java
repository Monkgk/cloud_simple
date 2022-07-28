package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class SeatSelected  implements Serializable {
    @Id
    private int seat_id;
    private int session_id;
}
