package com.example.orderfood.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    @Test
    public void ShoppingcartTest(){
        CartItem item = new CartItem();
        item.setId(new CartItemId());
        item.setFoodImage("");
        item.setUnitPrice(new BigDecimal(0));
        item.setQuantity(1);

        System.out.println(item.toString());
        CartItem item2 = CartItem.builder()
                .id(new CartItemId())
                .foodImage("")
                .build();
        System.out.println(item2.toString());
    }

}