package com.example.orderfood.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    private String id;
    private String userId;
    private BigDecimal totalPrice;
    private String customer;
    private String phone;
    private String note;
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private Set<CartItem> items;

    public void addTotalPrice(CartItem cartItem) {
        if(this.totalPrice == null){
            this.totalPrice = new BigDecimal(0);
        }
        BigDecimal quantityInBigDecimal = new BigDecimal(cartItem.getQuantity());
        this.totalPrice.add(cartItem.getUnitPrice().multiply(quantityInBigDecimal));
    }
}
