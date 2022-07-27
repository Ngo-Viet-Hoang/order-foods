package com.example.orderfood.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable

@NoArgsConstructor
@AllArgsConstructor
public class CartItemId implements Serializable {
    @Column(name = "shopping_cart_id")
    private String shoppingCartId;
    @Column(name = "food_id")
    private String foodId;
}
