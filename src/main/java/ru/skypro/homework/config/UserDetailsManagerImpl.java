package ru.skypro.homework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import ru.skypro.homework.entities.User;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.UserServiceImpl;

@Component
public class UserDetailsManagerImpl implements UserDetailsManager {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailsManagerImpl(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(UserDetailsImpl::new)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();
        String userEmail = userDetails.getUsername();
        String usernameBeforeAtSymbol = userEmail.substring(0, userEmail.indexOf('@'));

        user.setEmail(userEmail);
        user.setUsername(usernameBeforeAtSymbol);
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getAuthorities().iterator().next().getAuthority().substring(5));
        userRepository.save(user);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        UserDetails userDetails = loadUserByUsername(username);

        UserDetails updatedUser = org.springframework.security.core.userdetails.User.withUserDetails(userDetails)
                .password(passwordEncoder.encode(newPassword))
                .build();
        User user = userRepository.findByEmail(username).get();
        user.setPassword(updatedUser.getPassword());
        userService.updateUser(user);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }
}
