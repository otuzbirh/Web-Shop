package com.zadaca.webshop.service;



import com.zadaca.webshop.dto.UserRegistrationDto;
import com.zadaca.webshop.model.Role;
import com.zadaca.webshop.model.User;
import com.zadaca.webshop.repository.RoleRepository;
import com.zadaca.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public User save(UserRegistrationDto registrationDto, HttpServletRequest request) {

       Optional<User> o= Optional.ofNullable(userRepository.findByEmail(registrationDto.getEmail()));
       if(o.isPresent()){
           throw new RuntimeException("Email se koristi");

       }else {
           User user = new User();
           user.setFirstName(registrationDto.getFirstName());
           user.setLastName(registrationDto.getLastName());

           user.setEmail(registrationDto.getEmail());

           user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));


           Role role;
           if (roleRepository.findByName("ROLE_USER") != null) {
               role = roleRepository.findByName("ROLE_USER");


           } else {
               role = new Role();
               role.setName("ROLE_USER");
           }
           user.setRoles(new HashSet<Role>(Arrays.asList(role)));





           return userRepository.save(user);
       }

    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);



            if (user == null) {
                throw new UsernameNotFoundException("Invalid username or password.");
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));

    }

    private Set<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    }



}
