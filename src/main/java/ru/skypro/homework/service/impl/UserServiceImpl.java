package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.dto.mapper.UserDtoMapper;
import ru.skypro.homework.entities.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDtoMapper::mapToUserDto)
                .toList();
    }

    public UserDto createUser(UserDto adDto) {
        return UserDtoMapper.mapToUserDto(userRepository.save(userDtoMapper.mapToUser(adDto)));
    }

    public UserDto updateUser(UserDto adDto) {
        return UserDtoMapper.mapToUserDto(userRepository.save(userDtoMapper.mapToUser(adDto)));
    }

    public UserDto findUser(Long id) {
        return UserDtoMapper.mapToUserDto(userRepository.findById(id).get());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public UserDto getLoggedInUser() {
        return null;
    }

    @Override
    public UserDto updateUserDetails(UpdateUserDto updateUser) {
        return null;
    }
    @Override
    public UserDto findByEmail(String email) {
        User findedUser = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserDtoMapper.mapToUserDto(findedUser);
    }
}
