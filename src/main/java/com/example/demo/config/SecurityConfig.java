package com.example.demo.config;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// SecurityConfig class sets up authentication, authorization, and JWT token validation for the Spring Security module in the application.
// Configure Spring Security in configuration file
// Override configure methods to set up security rules, authentication manager, and add JWT filter
// @Configuration: Indicates that this class has @Bean definitions that should be processed by the
// Spring container to generate bean definitions and service requests for those beans at runtime
// SecurityFilterChain: is an interface in Spring Security that represents a filter chain
// configuration for web security. It allows you to define the security configurations for different
// HTTP requests, such as authentication, authorization, exception handling, and session management.
@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    A custom implementation of UserDetailsService to fetch user details for authentication.
    @Autowired
    private CustomUserDetailsService userDetailsService;

//    A filter that intercepts requests to validate JWT tokens.
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

//   Handles exceptions when an unauthenticated user tries to access a protected resource.
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

//  Defines a bean for BCryptPasswordEncoder to encode and verify passwords securely.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//  Defines a bean for AuthenticationManager using AuthenticationConfiguration.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

//  Defines a bean for SecurityFilterChain to configure security rules for different HTTP requests.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("securityFilterChain");
        httpSecurity.csrf(csrf -> csrf.disable())   // Disables CSRF protection.
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/authenticate/checkLoginCredentials", "/api/authenticate/signup").permitAll() // Allow public access to auth endpoints
                        .anyRequest().authenticated() // Secure all other endpoints
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint)) // Handle unauthorized access attempts
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Use stateless sessions
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return httpSecurity.build();
    }

}
