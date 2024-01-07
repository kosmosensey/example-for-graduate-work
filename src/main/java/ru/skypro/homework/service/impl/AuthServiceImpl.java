package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.config.UserDetailsManagerImpl;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserDetailsManagerImpl manager;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserDetailsManagerImpl manager,
                           PasswordEncoder passwordEncoder) {
        this.manager = manager;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDto register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        String pass = encoder.encode(register.getPassword());
        manager.createUser(register,pass);
        return true;
    }

}
