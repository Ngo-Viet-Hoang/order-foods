package com.example.orderfood.entity.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    private String id;
    private String name;
    private String description;
    private String detail;
    private String image;
    private BigDecimal price;
    private String createdAt;
    private String updatedAt;
    private String status;
}
