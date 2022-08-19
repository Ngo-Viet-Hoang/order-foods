package com.example.orderfood.controller;

import com.example.orderfood.entity.Account;
import com.example.orderfood.entity.Food;
import com.example.orderfood.entity.dto.AccountLoginDto;
import com.example.orderfood.entity.dto.AccountRegisterDto;
import com.example.orderfood.repository.AccountRepository;
import com.example.orderfood.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping(path = "api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    final AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    final PasswordEncoder passwordEncoder;
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody @Valid AccountRegisterDto accountRegisterDto) throws Exception {
        return ResponseEntity.ok(accountService.register(accountRegisterDto));
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
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


    //    @RequestMapping(method = RequestMethod.PUT)
//    public ResponseEntity<?> update( @RequestBody AccountRegisterDto accountRegisterDto){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<Account> account  = accountRepository.findById(Long.parseLong(principal.toString()));
//        if (!account.isPresent()) {
//            ResponseEntity.badRequest().build();
//        }
//        Account existAccount = account.get();
//        if (accountRegisterDto.getUsername() != null)
//            existAccount.setUsername(accountRegisterDto.getUsername());
//
//        if (accountRegisterDto.getPassword() != null)
//
//
//            existAccount.setPasswordHash(passwordEncoder.encode(accountRegisterDto.getPassword()));
//        if (accountRegisterDto.getEmail() != null)
//            existAccount.setEmail(accountRegisterDto.getEmail());
//        if (accountRegisterDto.getPhone() != null)
//            existAccount.setPhone(accountRegisterDto.getPhone());
//            existAccount.setRole(accountRegisterDto.getRole());
//
//        accountService.save(existAccount);
//        return ResponseEntity.ok(existAccount);
//
//    }
    @RequestMapping(method = RequestMethod.PUT,path = "{id}")
    public ResponseEntity<?> update( @RequestBody AccountRegisterDto accountRegisterDto){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<Account> account  = accountRepository.findById(Long.parseLong(principal.toString()));
        Optional<Account> account = accountService.findById(accountRegisterDto.getId());
        if (!account.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Account existAccount = account.get();
        if (accountRegisterDto.getUsername() != null)
            existAccount.setUsername(accountRegisterDto.getUsername());

        if (accountRegisterDto.getPassword() != null)


            existAccount.setPasswordHash(passwordEncoder.encode(accountRegisterDto.getPassword()));
        if (accountRegisterDto.getEmail() != null)
            existAccount.setEmail(accountRegisterDto.getEmail());
        if (accountRegisterDto.getPhone() != null)
            existAccount.setPhone(accountRegisterDto.getPhone());
        existAccount.setRole(accountRegisterDto.getRole());

        accountService.save(existAccount);
        return ResponseEntity.ok(existAccount);

    }
}
