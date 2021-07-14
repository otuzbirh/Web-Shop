package com.zadaca.webshop.service;


import com.zadaca.webshop.dto.UserRegistrationDto;
import com.zadaca.webshop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService extends UserDetailsService {
     User save(UserRegistrationDto registrationDto, HttpServletRequest request);



}
