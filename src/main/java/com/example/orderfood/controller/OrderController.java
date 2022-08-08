package com.example.orderfood.controller;

import com.example.orderfood.entity.Account;
import com.example.orderfood.entity.Food;
import com.example.orderfood.entity.Order;
import com.example.orderfood.entity.OrderDetail;
import com.example.orderfood.entity.entityEnum.OrderStatus;
import com.example.orderfood.entity.reqBody.ReqFood;
import com.example.orderfood.entity.reqBody.ReqOrder;
import com.example.orderfood.entity.search.FilterParameter;
import com.example.orderfood.entity.search.OrderSpecification;
import com.example.orderfood.entity.search.SearchCriteria;
import com.example.orderfood.entity.search.SearchCriteriaOperator;
import com.example.orderfood.repository.AccountRepository;
import com.example.orderfood.repository.OrderDetailRepository;
import com.example.orderfood.repository.OrderRepository;
import com.example.orderfood.service.AccountService;
import com.example.orderfood.service.FoodService;
import com.example.orderfood.service.OrderService;
import com.example.orderfood.service.TelegramBotService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/orders")
public class OrderController {

    Logger logger = Logger.getLogger(OrderController.class.toString());

    final  OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    FoodService foodService;
    @Autowired
    TelegramBotService telegramBotService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "") String keyword,
//            @RequestParam(defaultValue = "0") String userId,
            @RequestParam(defaultValue = "2") int status) {

        Specification<Order> specification = Specification.where(null);

        if (keyword != null && keyword.length() > 0) {
            SearchCriteria searchCriteria
                    = new SearchCriteria("keyword", SearchCriteriaOperator.JOIN, keyword);
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        if (status != 0) {
            SearchCriteria searchCriteria
                    = new SearchCriteria("status", SearchCriteriaOperator.EQUALS, status);
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        Page<Order> result = this.orderService.findAll(page, limit, specification);
        return ResponseEntity.ok().body(result);
    }
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderService.findById(id);
        if (!optionalOrder.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalOrder.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> findAllByOneObject(
            @RequestBody FilterParameter param) {
        Page<Order> result = this.orderService.findAll(param);
        return ResponseEntity.ok().body(result);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<?> orderFood(@RequestBody ReqOrder reqOrder) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();




        List<OrderDetail> orderDetails = new ArrayList<>();



        Optional<Account> account  = accountRepository.findById(Long.parseLong(principal.toString()));



        // create order
        Order order = new Order();
        order.setAccount(order.getAccount());
        order.setTotalPrice(order.getTotalPrice());
        order.setCreatedAt(order.getCreatedAt());
        order.setStatus(OrderStatus.DONE);
        // set data -> save order

//        logger.info("new order: " + newOrder.getId());


        if (account.isPresent()){
            order.setAccount(account.get());


        }else {

        }

        Order orderNew = orderRepository.save(order);

        logger.info("new order: " + orderNew.getId());

        // craete order detail
        List<ReqFood> list = reqOrder.getFoods();
        list.forEach(e -> {

            Food food = foodService.findById(e.getFoodId()).get();
            OrderDetail detail = new OrderDetail();

            logger.info("detail: " + detail.getOrderDetailId());
            detail.setOrder(orderNew);
            detail.setFood(food);
            detail.setUnitPrice(food.getPrice());
            detail.getQuantity();

//            logger.info(e.get("quantity").toString());
            detail.setQuantity(e.getQuantity());

            // set data


            orderDetails.add(detail);
        });


        orderNew.calTotalPrice(orderDetails);
        orderRepository.save(orderNew);
        orderDetailRepository.saveAll(orderDetails);
        telegramBotService.sendErrorToMe(order.getAccount().getUsername());


        return ResponseEntity.ok().body(reqOrder);
    }
}
