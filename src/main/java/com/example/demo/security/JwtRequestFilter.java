package com.example.demo.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// THIS IS THE JWT REQUEST FILTER WHICH INTERCEPTS REQUESTS AND VALIDATES JWT TOKENS. CALLED ONCE FOR EACH AND EVERY REQUEST
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

//    inject our customised User Service (to fetch user details of the user we are trying to authenticate)
    @Autowired
    private CustomUserDetailsService userDetailsService;

//    inject our JWT Utility class (to manipulate token and Subject)
    @Autowired
    private JwtUtil jwtUtil;


//  overridden to implement the logic for filtering requests
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

//  retrieves the Authorization header from the request to check for a Bearer token
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

//        Check if a Bearer token is present in the Authorization Header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // extract jwt
            username = jwtUtil.extractUsername(jwt);    // extract the Subject (username, email,... etc)
        }

//  If a token is present, the token will be verified and the authentication data will set for the user for that
//  Request by setting the authentication property of the SecurityContext using the SecurityContextHolder
//  Checks if the username is not null and if the current authentication context is null
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // load Subject (username, email)

//  Validate jwt token using the method in jwtUtil
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//  Authentication data will set for the user for that request by setting the authentication property of the SecurityContext using the SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
//  Proceed to the next filter in the chain
        chain.doFilter(request, response);
    }
}
