package com.example.orderfood.search;

import com.example.orderfood.OrderFoodApplication;
import com.example.orderfood.entity.Order;
import com.example.orderfood.entity.search.OrderSpecification;
import com.example.orderfood.entity.search.SearchCriteria;
import com.example.orderfood.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.orderfood.entity.search.SearchCriteriaOperator.EQUALS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderFoodApplication.class)
class OrderSpecificationTest {
    @Autowired
    OrderRepository orderRepository;
    @Test
    public void demoTest() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        OrderSpecification filter01 =
//                new OrderSpecification(new SearchCriteria("createdAt", "=", LocalDateTime.parse("2022-07-12 08:43:37",formatter)));
        OrderSpecification filter02 =
                new OrderSpecification(new SearchCriteria("id", EQUALS, 3));
        List<Order> orders = orderRepository.findAll((filter02));
        System.out.println(orders.size());

    }

}