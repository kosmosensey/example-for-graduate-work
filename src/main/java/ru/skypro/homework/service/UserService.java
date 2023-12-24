package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

public interface UserService {
    UserDto getLoggedInUser();

    UserDto updateUserDetails(UpdateUserDto updateUser);
}
