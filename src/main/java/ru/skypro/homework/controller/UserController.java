package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import ru.skypro.homework.service.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPassword) {
        if (isValidPassword(newPassword)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
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
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto updateUser) {
        UserDto updatedUserDto = userService.updateUserDetails(updateUser);
        if (updatedUserDto != null) {
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
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
