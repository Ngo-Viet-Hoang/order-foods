package com.example.orderfood.service;

import com.example.orderfood.OrderFoodApplication;
import com.example.orderfood.config.H2JpaConfig;
import com.example.orderfood.entity.*;
import com.example.orderfood.entity.dto.CartItemDTO;
import com.example.orderfood.entity.dto.ShoppingCartDTO;
import com.example.orderfood.repository.AccountRepository;
import com.example.orderfood.repository.FoodRepository;
import com.example.orderfood.repository.OrderRepository;
import com.example.orderfood.repository.ShoppingCartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrderFoodApplication.class})
public class OrderServiceTest {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private FoodRepository foodRepository;
    private String foodId01 = "112345";
    private String foodId02 = "1123453";


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AccountRepository accountRepository;


    @Before
    public void before() throws Exception {
        Food food1 = new Food();
        food1.setId(UUID.randomUUID().toString());
        food1.setName("Food 01");
        food1.setDescription("Helo");
        food1.setPrice(BigDecimal.valueOf(50));
        food1.setSlug("product-01");
       foodRepository.save(food1);

      Food food2 = new Food();
        food2.setId(UUID.randomUUID().toString());
        food2.setName("Food 02");
        food2.setDescription("Helo");
        food2.setPrice(BigDecimal.valueOf(50));
        food2.setSlug("product-02");
        foodRepository.save(food2);
    }
    @Test
    public void getAccount(){
        List<Account> accounts = accountRepository.findAll();
        System.out.println(accounts.size());
        if (accounts.size() == 0) {
            System.out.println("0");
        }
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println("account: "+ accounts.get(i).getId());
        }
    }
    @Test
    public void saveOrderSimple() {
        Order order = new Order();
        String orderId = UUID.randomUUID().toString();
        order.setId(orderId);
        order.setAccount(accountRepository.findById((long)1).get());
        order.setTotalPrice(new BigDecimal(0));

        Set<OrderDetail> orderDetailSet = new HashSet<>();

        Food food01 = foodRepository.findById(foodId01).get();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderDetailId(new OrderDetailId(orderId, food01.getId()));
        orderDetail.setOrder(order);
        orderDetail.setFood(food01);
        orderDetail.setQuantity(3);
        orderDetail.setUnitPrice(food01.getPrice());
        order.setTotalPrice(food01.getPrice().multiply(new BigDecimal(orderDetail.getQuantity())));
        orderDetailSet.add(orderDetail);

        Food food02 = foodRepository.findById(foodId02).get();
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setOrderDetailId(new OrderDetailId(orderId, food02.getId()));
        orderDetail2.setOrder(order);
        orderDetail2.setFood(food02);
        orderDetail2.setQuantity(5);
        orderDetail2.setUnitPrice(food02.getPrice());
        order.setTotalPrice(order.getTotalPrice().add(food02.getPrice().multiply(new BigDecimal(orderDetail2.getQuantity()))));
        orderDetailSet.add(orderDetail2);

        order.setOrderDetails(orderDetailSet);
        System.out.println("before save");
        orderRepository.save(order);

        Order savedOrder = orderRepository.findAll().get(0);
        System.out.println(savedOrder.getId());
        System.out.println(savedOrder.getTotalPrice());
        Set<OrderDetail> orderDetails = savedOrder.getOrderDetails();
        System.out.println(orderDetails.size());
        for (OrderDetail od :orderDetails) {
            System.out.println(od.getQuantity());
            System.out.println(od.getUnitPrice());
            System.out.println(od.getFood().getName());
        }
    }
    @Test
    public void realSaveOrder() {
        String userAccessToken = "";
        String userId = "A001";
        CartItemDTO cartItemDTO1 = CartItemDTO.builder()
                .foodId(foodId01)
                .quantity(5)
                .build();
        CartItemDTO cartItemDTO2 = CartItemDTO.builder()
                .foodId(foodId02)
                .quantity(3)
                .build();
        ShoppingCartDTO shoppingCartDTO = ShoppingCartDTO.builder()
                .account(accountRepository.findById((long)1).get())
                .phone("09123")
                .note("Ko cay")
                .customer("Hung")
                .build();
        HashSet<CartItemDTO> setItemDTO = new HashSet<>();
        setItemDTO.add(cartItemDTO1);
        setItemDTO.add(cartItemDTO2);
        shoppingCartDTO.setItems(setItemDTO);

        boolean hasException = false;
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .id(UUID.randomUUID().toString())
                .customer(shoppingCartDTO.getCustomer())
                .note(shoppingCartDTO.getNote())
                .phone(shoppingCartDTO.getPhone())
                .account(shoppingCartDTO.getAccount())
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
                    .id(new CartItemId(shoppingCart.getId(),food.getId()))
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
        System.out.println(shoppingCartRepository.findAll().size());
    }   @Test
    public void findAllShoppingCart() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        for (int i = 0; i < shoppingCarts.size(); i++) {
            System.out.println("Test");
            System.out.println(shoppingCarts.get(i).getNote());
            System.out.println(shoppingCarts.get(i).getId());
            System.out.println(shoppingCarts.get(i).getPhone());
        }
    }
}