package com.store.pizza.jwt.controller;


import com.store.pizza.jwt.request.JWTRequest;
import com.store.pizza.jwt.response.JWTResponse;
import com.store.pizza.jwt.service.CustomerUserDetailsService;
import com.store.pizza.jwt.util.JWTUtil;
import com.store.pizza.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/token")
    public JWTResponse getToken(@RequestBody JWTRequest jwtRequest)throws UsernameNotFoundException {

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
                    jwtRequest.getPassword()));
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new UsernameNotFoundException("Bad Credentials");
        }

        UserDetails userDetails = this.customerUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println(token);
        return new JWTResponse(token);

    }
}








