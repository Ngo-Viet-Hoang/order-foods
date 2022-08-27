package com.example.orderfood.repository;

import com.example.orderfood.entity.Account;
import com.example.orderfood.entity.ViewOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewOrderRepository extends JpaRepository<ViewOrder, Long> {

    List<ViewOrder> findByAccountId(long id);
    List<ViewOrder> deleteByAccountId(long id);

}
