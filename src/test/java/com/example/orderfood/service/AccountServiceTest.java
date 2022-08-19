package com.example.orderfood.service;

import com.example.orderfood.OrderFoodApplication;
import com.example.orderfood.entity.Account;
import com.example.orderfood.entity.Credential;
import com.example.orderfood.entity.dto.AccountLoginDto;
import com.example.orderfood.entity.dto.AccountRegisterDto;
import com.example.orderfood.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderFoodApplication.class)
class AccountServiceTest {

    @Autowired
    AccountService accoutService;
    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("/(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}/");

        Matcher matcher = pattern.matcher("0369631596");
        System.out.println(matcher.matches());
    }

    @Test
    void register() {
        AccountRegisterDto accoutRegisterDto = new AccountRegisterDto();
        accoutRegisterDto.setUsername("viethoang");
        accoutRegisterDto.setPassword("1234453");
        accoutRegisterDto.setEmail("ngoviethoang@gmail.com");
        accoutRegisterDto.setPhone("0345758659");
        accoutRegisterDto.setRole(1);
        Account afterCreate = null;
        try {
            afterCreate = accoutService.register(accoutRegisterDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(afterCreate);
    }

    @Test
    void findAll() {
        String[] testStrings = {
                /* Following are valid phone number examples */
                "(123)4567890", "1234567890", "123-456-7890", "(123)456-7890",
                /* Following are invalid phone numbers */
                "(1234567890)","123)4567890", "12345678901", "(1)234567890",
                "(123)-4567890", "1", "12-3456-7890", "123-4567", "Hello world"};




//        List<Account> accountList = accountRepository.findAll();
//        for (Account account:accountList
//             ) {
//            String phone = account.getPhone();
//
//            System.out.println(phone.replaceFirst("^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{1,3}\\\\))|\\\\d{1,3})[- .]?\\\\d{3,4}[- .]?\\\\d{4}$", "($1) $2-$3"));
//        }

    }
    @Test
    void login() {
        AccountLoginDto accountLoginDto = new AccountLoginDto();
        accountLoginDto.setUsername("vieeg0445");
        accountLoginDto.setPassword("1234453");
        Credential credential = accoutService.login(accountLoginDto);
        System.out.println(credential.toString());
    }
    }