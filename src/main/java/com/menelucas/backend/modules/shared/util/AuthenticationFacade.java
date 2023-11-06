package com.menelucas.backend.modules.shared.util;

import com.menelucas.backend.exception.NoAuthenticationException;
import com.menelucas.backend.modules.user.model.User;
import com.menelucas.backend.modules.user.service.CustomUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    private final CustomUserDetailsService customUserDetailsService;

    public AuthenticationFacade(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new NoAuthenticationException("No user authentication exception or unauthorized");
        }
        String username = authentication.getName();
        return customUserDetailsService.getUserObjectByUsername(username);
    }
}

