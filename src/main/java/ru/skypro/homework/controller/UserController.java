package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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
import java.io.IOException;
import java.util.Objects;

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
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto updateUser, HttpServletRequest request,Authentication authentication) {
        if (authentication.getName() != null) {
            UpdateUserDto updatedUserDto = userService.updateUser(updateUser,authentication);
            return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestParam MultipartFile image,
                                                Authentication authentication) throws IOException {
//        if (!(Objects.requireNonNull(image.getContentType()).startsWith("image/"))) {
//            throw new InvalidMediaTypeException();
//        }
        try {
            userService.updateUserAvatar(image, authentication);
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException.Unauthorized e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    private boolean isValidPassword(NewPasswordDto newPassword) {

        return newPassword.getCurrentPassword().length() >= 8
               && newPassword.getNewPassword().length() >= 8; // Проверка длины
    }
}
