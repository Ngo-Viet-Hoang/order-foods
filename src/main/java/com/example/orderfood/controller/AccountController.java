package com.example.orderfood.controller;

import com.example.orderfood.entity.Account;
import com.example.orderfood.entity.Food;
import com.example.orderfood.entity.dto.AccountLoginDto;
import com.example.orderfood.entity.dto.AccountRegisterDto;
import com.example.orderfood.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping(path = "api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    final AccountService accountService;
    @RequestMapping(path = "register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody AccountRegisterDto accountRegisterDto){
        return ResponseEntity.ok(accountService.register(accountRegisterDto));
    }
    @RequestMapping(path = "login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AccountLoginDto accountLoginDto){
        return ResponseEntity.ok(accountService.login(accountLoginDto));
    }
    @RequestMapping(method = RequestMethod.GET)
    public String getInformation(){
        return "";
    }
    @RequestMapping(path = "{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<Account> optionalFood = accountService.findById(id);
        if (!optionalFood.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalFood.get());
    }
    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getList(){
        return ResponseEntity.ok(accountService.findAll());
    }
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account){
        Optional<Account> optionalAccount = accountService.findById(id);
        if (!optionalAccount.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Account existAccount = optionalAccount.get();
        if (account.getUsername() != null)
            existAccount.setUsername(account.getUsername());
        if (account.getPasswordHash() != null)
            account.setPasswordHash(account.getPasswordHash());
        if (account.getEmail() != null)
            account.setEmail(account.getEmail());
        if (account.getPhone() != null)
            account.setPhone(account.getPhone());
            account.setRole(account.getRole());

        accountService.save(existAccount);
        return ResponseEntity.ok().build();

    }



}
