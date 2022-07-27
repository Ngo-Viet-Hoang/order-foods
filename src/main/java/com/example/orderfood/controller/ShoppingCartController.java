package com.example.orderfood.controller;


import com.example.orderfood.entity.CartItem;
import com.example.orderfood.entity.CartItemId;
import com.example.orderfood.entity.Food;
import com.example.orderfood.entity.ShoppingCart;
import com.example.orderfood.entity.dto.CartItemDTO;
import com.example.orderfood.entity.dto.ShoppingCartDTO;
import com.example.orderfood.repository.FoodRepository;
import com.example.orderfood.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/shopping-carts")
public class ShoppingCartController {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    FoodRepository foodRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void saveCart(@RequestParam String userId, @RequestBody ShoppingCartDTO shoppingCartDTO){
        boolean hasException = false;
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .customer(shoppingCartDTO.getCustomer())
                .note(shoppingCartDTO.getNote())
                .phone(shoppingCartDTO.getPhone())
                .build();
        Set<CartItem> setCartItem = new HashSet<>();
        System.out.println(shoppingCart.getId());
        for (CartItemDTO cartItemDTO :
                shoppingCartDTO.getItems()) {
            Optional<Food> optionalFood = foodRepository.findById(cartItemDTO.getFoodId());
            if(!optionalFood.isPresent()){
                hasException = true;
                break;
            }
            Food food = optionalFood.get();
            CartItem cartItem = CartItem.builder()
                    .id(new CartItemId(shoppingCart.getId(), food.getId()))
                    .foodName(food.getName())
                    .foodImage(food.getImage())
                    .quantity(cartItemDTO.getQuantity())
                    .unitPrice(food.getPrice())
                    .shoppingCart(shoppingCart)
                    .build();

            shoppingCart.addTotalPrice(cartItem);
            setCartItem.add(cartItem);
        }
        shoppingCart.setItems(setCartItem);
        shoppingCartRepository.save(shoppingCart);
    }
}
