//package com.example.orderfood.controller;
//
//import com.example.orderfood.entity.*;
//import com.example.orderfood.entity.reqBody.ReqOrder;
//import com.example.orderfood.repository.AccountRepository;
//import com.example.orderfood.repository.OrderDetailRepository;
//import com.example.orderfood.service.AccountService;
//import com.example.orderfood.service.FoodService;
//import com.example.orderfood.service.OrderService;
//import com.example.orderfood.service.ViewOrderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@CrossOrigin("*")
//@RequestMapping(path = "api/v1/view/orders")
//
//public class ViewOrderController {
//    final ViewOrderService viewOrderService;
//    @Autowired
//    AccountService accountService;
//    @Autowired
//    AccountRepository accountRepository;
//    @Autowired
//    FoodService foodService;
//
//    public ViewOrderController(ViewOrderService viewOrderService) {
//        this.viewOrderService = viewOrderService;
//    }
//    @RequestMapping(method = RequestMethod.GET,path = "/list")
//    public ResponseEntity<ResponseData> getList(){
//        ResponseData responseData = new ResponseData("Success",200,viewOrderService.findAll());
//        return ResponseEntity.ok(responseData);
//    }
//    @RequestMapping(method = RequestMethod.GET, path = "{id}")
//    public ResponseEntity<?> getDetail(@PathVariable Long id) {
//        Optional<ViewOrder> optionalViewOrder = viewOrderService.findById(id);
//        if (!optionalViewOrder.isPresent()) {
//            ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(optionalViewOrder.get());
//    }
//    @RequestMapping(method = RequestMethod.POST, value = "/create")
//    public ResponseEntity<?> vieworderFood(@RequestBody ViewOrder reqOrder) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<Account> account = accountRepository.findById(Long.parseLong(principal.toString()));
//
//        ViewOrder viewOrder = new ViewOrder();
//        viewOrder.setAccount(viewOrder.getAccount());
//        viewOrder.setFood(viewOrder.getFood());
//        viewOrder.setQuantity(viewOrder.getQuantity());
//
//        return ResponseEntity.ok().body(viewOrder);
//
//    }
//
//
//
//    }
