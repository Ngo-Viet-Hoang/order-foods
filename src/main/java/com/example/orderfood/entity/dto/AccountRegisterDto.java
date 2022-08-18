package com.example.orderfood.entity.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountRegisterDto {
    private long id;
    @NotNull(message = "username shouldn't be null")
    private String username;
    private String password;
    private String confirmPassword;
    @Email(message = "invalid email address")
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "invalid phone number entered")
    private String phone;
    private int role;

}
