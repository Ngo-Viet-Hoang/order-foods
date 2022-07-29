package com.example.orderfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin("*")
@RequestMapping(path = "api/v1/admin")
public class AdminController {
    @RequestMapping(method = RequestMethod.GET)
    public String say(){
        return "Hello Admin";
    }
}
