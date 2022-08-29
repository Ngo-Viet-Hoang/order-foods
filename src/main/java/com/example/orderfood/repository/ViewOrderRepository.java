package com.example.orderfood.repository;

import com.example.orderfood.entity.Account;
import com.example.orderfood.entity.ViewOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ViewOrderRepository extends JpaRepository<ViewOrder, Long> {

    List<ViewOrder> findByAccountId(long id);

    @Modifying
    @Transactional
    void deleteByIdAndAccountId(long id,long accountId);


//    void deleteByAccountId(long accountId);




//    @Query(
//            nativeQuery = true,
//            value = "DELETE FROM view_orders WHERE id = ?1 and account_id = ?2"
//            )
//    void deleteByIdAndAccountIdNQ(long id,long accountId);


}
