package com.example.orderfood.repository;

import com.example.orderfood.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findAccountByUsername(String username);
    List<Account> findAccountsByPhoneOrEmailOrUsername(String phone, String email,String username);

//    Optional<Account> findById(long ID);

}
