package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getLoggedInUser() {
        return null;
    }

    @Override
    public UserDto updateUserDetails(UpdateUserDto updateUser) {
        return null;
    }
}
