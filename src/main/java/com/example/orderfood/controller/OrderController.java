package com.example.orderfood.controller;

import com.example.orderfood.entity.Order;
import com.example.orderfood.entity.search.FilterParameter;
import com.example.orderfood.entity.search.OrderSpecification;
import com.example.orderfood.entity.search.SearchCriteria;
import com.example.orderfood.entity.search.SearchCriteriaOperator;
import com.example.orderfood.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/orders")
public class OrderController {
    final OrderService orderService;

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
//        if (userId != null) {
//            SearchCriteria searchCriteria
//                    = new SearchCriteria("userId", SearchCriteriaOperator.EQUALS, userId);
//            OrderSpecification filter = new OrderSpecification(searchCriteria);
//            specification = specification.and(filter);
//        }
        Page<Order> result = this.orderService.findAll(page, limit, specification);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> findAllByOneObject(
            @RequestBody FilterParameter param) {
        Page<Order> result = this.orderService.findAll(param);
        return ResponseEntity.ok().body(result);
    }

}
