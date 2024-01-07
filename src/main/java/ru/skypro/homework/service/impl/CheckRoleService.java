package ru.skypro.homework.service.impl;

import org.springframework.security.core.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class CheckRoleService {

    public static boolean isAdminOrOwnerAd(Authentication authentication, String ownerAd) {
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        boolean isOwnerAd = authentication.getName().equals(ownerAd);

        return isAdmin || isOwnerAd;
    }
}
