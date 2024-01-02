package ru.skypro.homework.service;

import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entities.User;

public interface UserService {
    UserDto getLoggedInUser();

    UserDto updateUserDetails(UpdateUserDto updateUser);

    @Transactional
    UserDto updateUser(Integer id, UpdateUserDto updateUser);

    void updateUser(User user);

    UserDto findByEmail(String email);
}
