package com.zadaca.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showWelcome(){
        return "welcome";
    }

    @GetMapping("/login")
    public String formLogin(){

        return "login";



    }
}
