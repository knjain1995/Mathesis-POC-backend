package com.example.demo.security;

import com.example.demo.entities.SignUpInformation;
import com.example.demo.repositories.SignUpInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// @Service marks the class as a service component in Spring, making it a candidate for dependency injection
// UserDetailsService is a core interface in Spring Security used to retrieve user-related data
// used to provide the custom implementation to fetch the user details of the user trying to authenticate into the application
@Service
public class CustomUserDetailsService implements UserDetailsService {

//    Autowire my signup repo. I will be matching the login information with those who have signedup
    @Autowired
    private SignUpInformationRepository signUpInformationRepository;

//    Overriding the loadByUsername method of UserDetailsService interface to alter the way we verify a user.
//    Here I am using email from SignUpInformation
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//       Fetch user details from SignUpInformation using email ID as reference
        SignUpInformation user = signUpInformationRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found With Email: " + email));
        System.out.println("User: " + user);

//        Creates a list of authorities (roles) for the user.
//        If the user has a role, it's prefixed with "ROLE_" as per Spring Security convention. If no role, an empty list is used.
        List<GrantedAuthority> authorities = user.getRole() != null ?
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())) :
                Collections.emptyList();

//        Spring Security User object (which implements UserDetails) with the user's email as username, password, and authorities
        return new User(user.getEmail(), user.getPassword(), authorities);
//        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
