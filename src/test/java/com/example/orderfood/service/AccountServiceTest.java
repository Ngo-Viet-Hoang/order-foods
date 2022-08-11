package com.example.orderfood.service;

import com.example.orderfood.OrderFoodApplication;
import com.example.orderfood.entity.Credential;
import com.example.orderfood.entity.dto.AccountLoginDto;
import com.example.orderfood.entity.dto.AccountRegisterDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderFoodApplication.class)
class AccountServiceTest {

    @Autowired
    AccountService accoutService;

    @Test
    void register() {
        AccountRegisterDto accoutRegisterDto = new AccountRegisterDto();
        accoutRegisterDto.setUsername("viethoanrtweg0445");
        accoutRegisterDto.setPassword("1234453");
        accoutRegisterDto.setEmail("ngoviethoanrerg@gmail.com");
        accoutRegisterDto.setPhone("012345456789");
        accoutRegisterDto.setBirthday("222");
        accoutRegisterDto.setAddress("Ha Noi");
        accoutRegisterDto.setRole(1);
        AccountRegisterDto afterCreate = accoutService.register(accoutRegisterDto);
        System.out.println(afterCreate);
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