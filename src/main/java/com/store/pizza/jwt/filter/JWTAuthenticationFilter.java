package com.store.pizza.jwt.filter;

import com.store.pizza.jwt.service.CustomerUserDetailsService;
import com.store.pizza.jwt.util.JWTUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomerUserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestHeaderToken = request.getHeader("Authorization");
        String username = null;
        String jwtToken;

        if (requestHeaderToken != null && requestHeaderToken.startsWith("Bearer ")){
            jwtToken = requestHeaderToken.substring(7);
            try {
                username = this.jwtUtil.extractUsername(jwtToken);
            }catch (MalformedJwtException e){
                e.printStackTrace();
                throw new MalformedJwtException("Invalid Token");
            }
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (SecurityContextHolder.getContext().getAuthentication()==null){
                UsernamePasswordAuthenticationToken authenticationToken = new
                        UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }else {
                System.out.println("Invalid Token");
            }

        }
        filterChain.doFilter(request,response);
    }
}
