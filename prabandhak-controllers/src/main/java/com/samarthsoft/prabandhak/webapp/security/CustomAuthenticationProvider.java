package com.samarthsoft.prabandhak.webapp.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomAuthenticationProvider  implements AuthenticationProvider {
    private AuthenticationManager authenticationManager;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication autheticationObj = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authentication.getPrincipal(),
                    authentication.getCredentials()));
        if (autheticationObj.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(
                        authentication);
        else SecurityContextHolder.clearContext();
        return autheticationObj;
    }

    public boolean supports(Class<? extends Object> authentication) {
        return true;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}