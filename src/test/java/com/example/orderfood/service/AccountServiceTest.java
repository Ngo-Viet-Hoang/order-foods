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
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderFoodApplication.class)
class AccountServiceTest {

    @Autowired
    AccountService accoutService;
    @Autowired
    AccountRepository accountRepository;

//    @Test
//    void register() {
//        AccountRegisterDto accoutRegisterDto = new AccountRegisterDto();
//        accoutRegisterDto.setUsername("viethoanrtweg0445");
//        accoutRegisterDto.setPassword("1234453");
//        accoutRegisterDto.setEmail("ngoviethoanrertrtrg@gmail.com");
//        accoutRegisterDto.setPhone("012345456789");
//        accoutRegisterDto.setRole(1);
//        AccountRegisterDto afterCreate = accoutService.register(accoutRegisterDto);
//        System.out.println(afterCreate);
//    }

    @Test
    void findAll() {
        String[] testStrings = {
                /* Following are valid phone number examples */
                "(123)4567890", "1234567890", "123-456-7890", "(123)456-7890",
                /* Following are invalid phone numbers */
                "(1234567890)","123)4567890", "12345678901", "(1)234567890",
                "(123)-4567890", "1", "12-3456-7890", "123-4567", "Hello world"};

        List<Account> accountList = accountRepository.findAll();
        for (Account account:accountList
             ) {
            String phone = account.getPhone();

            System.out.println(phone.replaceFirst("^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{1,3}\\\\))|\\\\d{1,3})[- .]?\\\\d{3,4}[- .]?\\\\d{4}$", "($1) $2-$3"));
        }

    }
    @Test
    void login() {
        AccountLoginDto accountLoginDto = new AccountLoginDto();
        accountLoginDto.setUsername("viethoanrtweg0445");
        accountLoginDto.setPassword("1234453");
        Credential credential = accoutService.login(accountLoginDto);
        System.out.println(credential.toString());
    }
    }