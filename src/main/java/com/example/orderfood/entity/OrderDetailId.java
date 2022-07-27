package com.example.orderfood.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class OrderDetailId implements Serializable {
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "food_id")
    private String foodId;
}