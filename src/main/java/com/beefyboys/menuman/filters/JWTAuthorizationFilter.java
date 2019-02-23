package com.beefyboys.menuman.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.beefyboys.menuman.models.AccountRole;
import com.beefyboys.menuman.services.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.beefyboys.menuman.security.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private TokenService tokenService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, TokenService tokenService) {
        super(authManager);
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        String bearerToken = request.getHeader(HEADER_STRING);

        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(bearerToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String bearerToken) {
        DecodedJWT decodedJWT = tokenService.decodeToken(bearerToken.replace(TOKEN_PREFIX, ""));
        GrantedAuthority authority = AccountRole.valueOf(decodedJWT.getClaim(ROLE_CLAIM_KEY).asString());
        return new UsernamePasswordAuthenticationToken(
                decodedJWT.getSubject(),
                null,
                Collections.singletonList(authority)
        );
    }
}
