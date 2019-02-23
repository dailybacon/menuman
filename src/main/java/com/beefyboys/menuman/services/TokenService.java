package com.beefyboys.menuman.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

import static com.auth0.jwt.JWT.require;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.beefyboys.menuman.security.SecurityConstants.*;

@Service
public class TokenService {

    private static Algorithm JWT_ALGORITHM = HMAC512(SECRET.getBytes());
    private static JWTVerifier JWT_VERIFIER = require(JWT_ALGORITHM).build();

    public String generateToken(User user) {
        String role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim(ROLE_CLAIM_KEY, role)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(JWT_ALGORITHM);
    }

    public DecodedJWT decodeToken(String jwt) {
       return JWT_VERIFIER.verify(jwt);
    }

}
