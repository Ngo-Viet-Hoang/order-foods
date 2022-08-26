package com.example.orderfood.service;

import com.example.orderfood.entity.Food;
import com.example.orderfood.entity.Order;
import com.example.orderfood.entity.entityEnum.OrderStatus;
import com.example.orderfood.entity.search.FilterParameter;
import com.example.orderfood.entity.search.OrderSpecification;
import com.example.orderfood.entity.search.SearchCriteria;
import com.example.orderfood.entity.search.SearchCriteriaOperator;
import com.example.orderfood.repository.OrderRepository;
import com.example.orderfood.util.JwtUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.JoinColumn;
import java.util.Optional;

@Service
public class OrderService {
    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    public Page<Order> findAll(int page, int limit,
                               Specification<Order> orderSpecification) {

        Sort sort = Sort.by(Sort.Direction.ASC, "orderStatus").and(Sort.by(Sort.Direction.DESC,"createdAt"));
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return orderRepository.findAll(orderSpecification, pageRequest);
    }


    public Page<Order> findByOrderStatus(int page, int limit, String status) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return orderRepository.findByOrderStatus(JwtUtil.searchEnum(OrderStatus.class,status), pageRequest);
    }
    public Page<Order> findAll(FilterParameter param) {
        Specification<Order> specification = Specification.where(null);
        if (param.getKeyword() != null && param.getKeyword().length() > 0) {
            SearchCriteria searchCriteria
                    = new SearchCriteria("keyword", SearchCriteriaOperator.JOIN, param.getKeyword());
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        if (param.getStatus() != 0) {
            SearchCriteria searchCriteria
                    = new SearchCriteria("status", SearchCriteriaOperator.EQUALS, param.getStatus());
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        if (param.getUserId() != null) {
            SearchCriteria searchCriteria
                    = new SearchCriteria("userId", SearchCriteriaOperator.EQUALS, param.getUserId());
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        return orderRepository.findAll(
                specification, PageRequest.of(param.getPage() - 1, param.getLimit(), Sort.by(Sort.Direction.ASC, "orderStatus")));
    }
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
    public Order save(Order order) {
        return orderRepository.save(order);
    }

}
