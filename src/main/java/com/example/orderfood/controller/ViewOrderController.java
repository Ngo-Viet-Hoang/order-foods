package com.example.orderfood.controller;

import com.example.orderfood.entity.*;
import com.example.orderfood.entity.dto.ViewOrderDto;
import com.example.orderfood.entity.reqBody.ReqFood;
import com.example.orderfood.entity.reqBody.ReqOrder;
import com.example.orderfood.entity.reqBody.ReqViewOrder;
import com.example.orderfood.repository.AccountRepository;
import com.example.orderfood.repository.FoodRepository;
import com.example.orderfood.repository.OrderDetailRepository;
import com.example.orderfood.repository.ViewOrderRepository;
import com.example.orderfood.service.AccountService;
import com.example.orderfood.service.FoodService;
import com.example.orderfood.service.OrderService;
import com.example.orderfood.service.ViewOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@CrossOrigin("*")
@RequestMapping(path = "api/v1/view/orders")

public class ViewOrderController {
    final ViewOrderService viewOrderService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    FoodService foodService;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    ViewOrderRepository viewOrderRepository;

    public ViewOrderController(ViewOrderService viewOrderService) {
        this.viewOrderService = viewOrderService;
    }
    @RequestMapping(method = RequestMethod.GET,path = "/list")
    public ResponseEntity<ResponseData> getList(){
        ResponseData responseData = new ResponseData("Success",200,viewOrderService.findAll());
        return ok(responseData);
    }
    @RequestMapping(method = RequestMethod.GET,path = "account")
    public ResponseEntity<?> getByAccount(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Account> account = accountRepository.findById(Long.parseLong(principal.toString()));
        ViewOrderDto viewOrderDto = new ViewOrderDto();
        List<ViewOrder> viewOrders = viewOrderRepository.findByAccountId(account.get().getId());
        viewOrderDto.setViewOrders(viewOrders);
        viewOrderDto.calTotalPrice(viewOrders);


        return ResponseEntity.ok(viewOrderDto);
    }
//    @RequestMapping(method = RequestMethod.DELETE,path = "account")
//    public ResponseEntity<ResponseData> deleteByAccount(){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<Account> account = accountRepository.findById(Long.parseLong(principal.toString()));
//        List<ViewOrder> viewOrders = viewOrderRepository.deleteByAccountId(account.get().getId());
//        ResponseData responseData = new ResponseData("Success",200,viewOrders);
//        return ResponseEntity.ok(responseData);
//    }



    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<ViewOrder> optionalViewOrder = viewOrderService.findById(id);
        if (!optionalViewOrder.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ok(optionalViewOrder.get());
    }
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<ResponseData> viewOrderFood(@RequestBody List<ReqFood> foods) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Account> account = accountRepository.findById(Long.parseLong(principal.toString()));
        List<ViewOrder> list = new ArrayList<>();
        foods.forEach(e -> {
            Optional<Food> food = foodRepository.findById(e.getFoodId());
            if(account.isPresent()){
                account.get();
            }
            if(food.isPresent()){
                e.getFoodId();
            }

            ViewOrder order = new ViewOrder();
            order.setFood(food.get());
            order.setQuantity(e.getQuantity());
            order.setAccount(account.get());
            list.add(order);
        });


        ResponseData responseData = new ResponseData("Success",200,viewOrderRepository.saveAll(list));
        return ok(responseData);
    }


    }
