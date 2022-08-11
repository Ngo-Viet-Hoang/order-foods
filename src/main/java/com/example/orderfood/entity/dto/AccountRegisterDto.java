package com.example.orderfood.entity.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountRegisterDto {
    private long id;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String phone;
    private String birthday;
    private String address;
    private int role;

}
