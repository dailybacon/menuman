package com.beefyboys.menuman.controllers;

import com.beefyboys.menuman.models.AuthenticationRequest;
import com.beefyboys.menuman.models.JwtResponse;
import com.beefyboys.menuman.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public JwtResponse createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new JwtResponse(tokenService.generateToken((User) authentication.getPrincipal()));
    }

}
