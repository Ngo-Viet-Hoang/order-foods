package com.example.orderfood.repository;

import com.example.orderfood.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findAccountByUsername(String username);

//    Optional<Account> findById(long ID);

}
