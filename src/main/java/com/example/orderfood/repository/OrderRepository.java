package com.example.orderfood.repository;

import com.example.orderfood.entity.Order;
import com.example.orderfood.entity.entityEnum.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order,Long>, JpaSpecificationExecutor<Order> {


    Page<Order> findByOrderStatus(OrderStatus orderStatus, PageRequest pageRequest);
}
