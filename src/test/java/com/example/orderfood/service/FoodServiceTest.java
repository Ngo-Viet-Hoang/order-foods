package com.example.orderfood.service;

import com.example.orderfood.OrderFoodApplication;
import com.example.orderfood.entity.*;
import com.example.orderfood.entity.entityEnum.FoodStatus;
import com.example.orderfood.entity.entityEnum.OrderStatus;
import com.example.orderfood.repository.CategoryRepository;
import com.example.orderfood.repository.FoodRepository;
import com.example.orderfood.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderFoodApplication.class)

class FoodServiceTest {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OrderRepository orderRepository;



    @Test
    public void testFood() {

        Optional<Category> category = Optional.of(categoryRepository.findById(1L).get());

        Food food1 = Food.builder()
                .id(UUID.randomUUID().toString())
                .name("com1234")
                .slug("com")
                .image("https://via.placeholder.com/150/92c952")
                .price(BigDecimal.valueOf(12))
                .description("hello")
                .status(FoodStatus.SALE)
                .category(category.get())
                .build();
        foodRepository.save(food1);

        List<Food> foods = foodRepository.findAll();
        foods.forEach(e->{
            System.out.printf("Foods: "+ e.getName());
        });
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .status(OrderStatus.DONE)
                .build();
        System.out.println("Order: "+ order.getId());
        HashSet<OrderDetail> orderDetails = new HashSet<>();
        for(Food food :
                foods){
            OrderDetailId orderDetailId = new OrderDetailId();
            orderDetailId.setOrderId(order.getId());
            orderDetailId.setFoodId(food.getId());
            OrderDetail orderDetail = OrderDetail.builder()
                    .orderDetailId(orderDetailId)
                    .order(order)
                    .food(food)
                    .quantity(10)
                    .unitPrice(new BigDecimal(2222))
                    .build();
            orderDetails.add(orderDetail);
        }

        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        System.out.println("Hello");
    }
}