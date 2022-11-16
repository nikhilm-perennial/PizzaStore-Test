package com.store.pizza.service.impl;

import com.store.pizza.jwt.config.SecurityConfig;
import com.store.pizza.model.User;
import com.store.pizza.repository.UserRepository;
import com.store.pizza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public User saveUser(User user)throws UsernameNotFoundException {
        if (userRepository.findUserByUserName(user.getUserName())==null){
            user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
            return userRepository.save(user);
        }else {
            throw new UsernameNotFoundException("UserName not Found: "+user.getUserName());
        }

    }
}
