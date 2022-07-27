package com.example.orderfood.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_items")
public class CartItem {
    @EmbeddedId
    private CartItemId id;
    private String foodName;
    private String foodImage;
    private int quantity;
    private BigDecimal unitPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;
}
