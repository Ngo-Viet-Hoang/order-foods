package com.example.orderfood.entity.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    private String name;
    private String image;
    private BigDecimal price;
    private String status;
}
