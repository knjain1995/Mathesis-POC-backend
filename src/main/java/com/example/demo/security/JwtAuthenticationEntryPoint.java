package com.example.demo.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

// CREATED TO HANDLE EXCEPTION WHEN AN UNAUTHENTICATED USER TRIES TO ACCESS A LOCKED RESOURCE
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // Handles exception when an unauthenticated user tries to access a locked resource
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);    // set response error as unauthorized

        PrintWriter writer = response.getWriter();
        writer.println("Access Denied!!"+authException.getMessage());   // print in console
    }

}
