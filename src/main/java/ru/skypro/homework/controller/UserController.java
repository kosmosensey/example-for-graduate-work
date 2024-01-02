package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.UserDetailsManagerImpl;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import ru.skypro.homework.entities.User;
import ru.skypro.homework.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserDetailsManagerImpl userDetailsManager;

    public UserController(UserService userService, UserDetailsManagerImpl userDetailsManager) {
        this.userService = userService;
        this.userDetailsManager = userDetailsManager;
    }

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPassword, HttpServletRequest request) {
        String userNameEmail = request.getRemoteUser();
        log.info("Changing password for user: {}", userNameEmail);
        userDetailsManager.changePassword(userNameEmail, newPassword.getNewPassword());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        UserDto userDto = userService.getLoggedInUser();
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto updateUser, HttpServletRequest request) {
        String userNameEmail = request.getRemoteUser();
        UserDto user = userService.findByEmail(userNameEmail);
        UserDto updatedUserDto = userService.updateUser(user.getId(), updateUser);
        log.info("Adding information for user: {}", updatedUserDto.getEmail());
        if (updatedUserDto != null) {
            return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    private boolean isValidPassword(NewPasswordDto newPassword) {

        return newPassword.getCurrentPassword().length() >= 8
               && newPassword.getNewPassword().length() >= 8; // Проверка длины
    }
}
