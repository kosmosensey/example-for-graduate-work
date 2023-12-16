package ru.skypro.homework.service;

import ru.skypro.homework.dto.users.UpdateUser;
import ru.skypro.homework.dto.users.UserDto;

public interface UserService {
    UserDto getLoggedInUser();

    UserDto updateUserDetails(UpdateUser updateUser);
}
