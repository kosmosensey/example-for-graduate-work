package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class ValidationService {

    public static boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList()
                .contains("ROLE_ADMIN");
    }

    public static boolean isOwner(Authentication authentication, String userName) {
        return authentication.getName().equals(userName);
    }
}
