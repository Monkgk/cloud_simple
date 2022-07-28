package com.common.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Refund {
    @Id
    private int id;
    private String  refund_request_no;
    private int refund_status;
    private float   refund_amount;
    private String  refund_reason;
}
