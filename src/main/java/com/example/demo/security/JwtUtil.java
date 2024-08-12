package com.example.demo.security;

import com.example.demo.enums.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// @Component: signifies that the class is a Spring managed Bean
// Create utility class to implement methods for extracting username, expiration data and claims; and generate, create, and validate jwttokens
@Component
public class JwtUtil {

    // secret value defined in properties file (injected here)
    @Value("${jwt_secret}")
    private String secret;

    // expiration period defined in properties file (injected here)
    @Value("${jwt_expiration}")
    private Long expiration;

    // extract the Subject claim from token (can be username, email etc., based on what we define) {Used in request filter}
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // extract the expiration claim from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // claimsResolver: A Function interface that takes a Claims object and returns a value of type T.
    // This function is used to resolve the desired claim from the Claims object.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);  // retrieve all claims from the provided JWT token.
        return claimsResolver.apply(claims);    // applies the claimsResolver function to the obtained Claims object to extract the specific claim of type T
    }

//    Jwts.parserBuilder(): This method is used to obtain a builder for constructing a JWT parser. It allows for configuring the parser before actually parsing the JWT token.
//    setSigningKey(secret.getBytes()): This part sets the signing key used to verify the digital signature of the JWT token. The secret is retrieved from the class field and converted to bytes before being set as the signing key.
//    build(): This method finalizes the configuration of the parser and builds the parser instance based on the provided settings.
//    parseClaimsJws(token): This method parses the provided JWT token and returns a Jws<Claims> object, which represents the parsed JWT token along with its claims.
//    getBody(): This method retrieves the body of the parsed JWT token, which contains all the claims (such as issuer, subject, expiration, etc.) embedded within the token.
//    EXTRACTS ALL CLAIMS FROM THE TOKEN
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody();
    }

//    check if token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

//    Generate a token using the user details {called in AuthenticationController}
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();   // create a variable called claim of the type Hashmap

        // Get list of authorities
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        System.out.println("ROLES: " + roles);
        claims.put("roles", roles); // Changed key to "roles"

        return createToken(claims, userDetails.getUsername());  // call the create method with parameters (claims and the Subject for the token)
    }

//    function to create token <params are an empty map and subject string (username, email, .. etc)>
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))   // Sets the expiration date of the token based on the current time plus the expiration period defined in the configuration file.
                .signWith(SignatureAlgorithm.HS256, secret.getBytes()).compact();   // Signs the token using the HS256 algorithm and the secret key converted to bytes.
    }

//    function to validate token.
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // extract username
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));  // return that token is valid? and that token is not expired
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        System.out.println("CLAIMS IN EXTRACT ROLES: "+claims);
        return (List<String>) claims.get("roles"); // Updated to match the new key
    }

//    public List<String> extractRoles(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//        return (List<String>) claims.get("role"); // Extract roles from claims
//    }

//    public List<String> extractRoles(String token) {
//        Claims claims = extractAllClaims(token);
//        return (List<String>) claims.get("role"); // Ensure this matches how you put the roles in the claims
//    }
}