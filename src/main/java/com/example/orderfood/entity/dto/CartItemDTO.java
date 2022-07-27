package com.example.orderfood.entity.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDTO {
    private String foodId;
    private int quantity;
}
