package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entities.User;

import java.io.IOException;

public interface UserService {
    void setPassword(String currentPassword, String newPassword, Authentication authentication);

    UserDto getLoggedInUser(Authentication authentication);

    UpdateUserDto updateUser(UpdateUserDto user,Authentication authentication);

    UserDto findByEmail(String email);

    void updateUserAvatar(MultipartFile image, Authentication authentication) throws IOException;

    byte[] getImage(Integer id) throws IOException;

    User getUser(String userName);
}
