package com.zadaca.webshop.controller;


import com.zadaca.webshop.dto.UserRegistrationDto;
import com.zadaca.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;



@Controller
public class UserRegistrationController {

    @Autowired
    private UserService userService;




    @GetMapping("/registration")
    public String FormRegistraion(Model model, UserRegistrationDto userDto){
        model.addAttribute("user",userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid UserRegistrationDto userDto, BindingResult bindingResult, Model model, HttpServletRequest request,
                               RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            model.addAttribute("user",userDto);
            model.addAttribute("bindingResult",bindingResult);
            return "registration";
        }else if(userDto.checkPwd()==false){
            model.addAttribute("user",userDto);
            model.addAttribute("message","Molimo vas unseite istu sifru u oba slucaja");
            return "registration";
        } else{
            try{

                userService.save(userDto,request);
                redirectAttributes.addFlashAttribute("message","Uspjesna registracija.");
                return "redirect:/login";
            }catch(RuntimeException r){
                model.addAttribute("message", r.getMessage());
                model.addAttribute("user",userDto);
                return "registration";
            }
        }
    }






}
