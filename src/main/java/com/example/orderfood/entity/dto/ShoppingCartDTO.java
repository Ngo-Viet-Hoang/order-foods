package com.example.orderfood.entity.dto;


import com.example.orderfood.entity.Account;
import com.example.orderfood.entity.ShoppingCart;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartDTO {
    private String id;
    private Account account;
    private BigDecimal totalPrice;
    private String customer;
    private String phone;
    private String note;
    private Set<CartItemDTO> items;

    public ShoppingCart generateCart(){
        return new ShoppingCart();
    }
}
