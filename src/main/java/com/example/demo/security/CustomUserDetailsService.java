package com.example.demo.security;

import com.example.demo.entities.SignUpInformation;
import com.example.demo.repositories.SignUpInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        SignUpInformation user = signUpInformationRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        System.out.println("User: " + user);
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
