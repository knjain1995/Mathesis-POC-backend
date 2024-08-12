package com.example.demo.controller;

import com.example.demo.dto.SignUpInformationDTO;
import com.example.demo.entities.LoginInformation;
import com.example.demo.enums.Role;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.SignUpInformationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private SignUpInformationService signUpInformationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

//    @GetMapping("/api/checkRole")
//    public ResponseEntity<?> checkRole(HttpServletRequest request) {
//        String authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String jwt = authorizationHeader.substring(7);
//            List<String> roles = jwtUtil.extractRoles(jwt); // Extract roles from the token
//            System.out.println("Roles: " + roles);
//            return ResponseEntity.ok(roles); // Return roles as response
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token provided");
//    }


    @PostMapping("/api/authenticate/checkLoginCredentials")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginInformation loginInformation) throws Exception {
        System.out.println("loginInformation.getEmail(): "+loginInformation.getEmail());
        System.out.println("loginInformation.getPassword(): "+loginInformation.getPassword());
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginInformation.getEmail(), loginInformation.getPassword());
            System.out.println("UsernamePasswordAuthenticationToken: "+authenticationToken);
            authenticationManager.authenticate(authenticationToken);
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginInformation.getEmail(), loginInformation.getPassword()));
        } catch (Exception e) {
            System.out.println("Authentication Failed");
            return new ResponseEntity<>("Incorrect Credentials!", HttpStatus.UNAUTHORIZED);
//            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginInformation.getEmail());
        System.out.println("userDetails: "+userDetails);
        final String jwt = jwtUtil.generateToken(userDetails);
        System.out.println("jwt: "+jwt);

        AuthenticationResponse response = new AuthenticationResponse(jwt, userDetails.getAuthorities().toArray()[0].toString());

        System.out.println("RESPONSE JWT: "+response.getJwt());
        System.out.println("RESPONSE ROLE: "+response.getRole());

        return new ResponseEntity<>(response, HttpStatus.OK);
//        return ResponseEntity<>()
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    // maps HTTP POST requests to api/signup to the method signUpInformation
    @PostMapping("/api/authenticate/signup")
    public ResponseEntity<?> signUpInformation(@RequestBody SignUpInformationDTO signUpInformationDTO) {  //get signup information as response body

//        final UserDetails userDetails = userDetailsService.loadUserByUsername(signUpInformationDTO.getEmail());
//        System.out.println("userDetails: "+userDetails);
//        final String jwt = jwtUtil.generateToken(userDetails);
//        System.out.println("jwt: "+jwt);
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));

        return signUpInformationService.createSignUpInformation(signUpInformationDTO);
    }
}

class AuthenticationResponse {
    private final String jwt;
    private final String role;

    public AuthenticationResponse(String jwt, String role) {
        this.jwt = jwt;
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public String getRole() {
        return role;
    }
}