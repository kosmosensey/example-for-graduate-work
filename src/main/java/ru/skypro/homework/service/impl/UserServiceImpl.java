package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.users.UpdateUser;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getLoggedInUser() {
        return null;
    }

    @Override
    public UserDto updateUserDetails(UpdateUser updateUser) {
        return null;
    }
}
