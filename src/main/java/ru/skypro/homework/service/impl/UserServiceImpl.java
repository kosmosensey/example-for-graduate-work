package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.dto.mapper.UpdateUserMapper;
import ru.skypro.homework.dto.mapper.UserDtoMapper;
import ru.skypro.homework.entities.Image;
import ru.skypro.homework.entities.User;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final UpdateUserMapper updateUserMapper;
    private final ImageService imageService;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userDtoMapper::mapToUserDto)
                .toList();
    }

    public UserDto createUser(UserDto userDto) {
        return userDtoMapper.mapToUserDto(userRepository.save(userDtoMapper.mapToUser(userDto)));
    }

    public UserDto findUser(Integer id) {
        return userDtoMapper.mapToUserDto(userRepository.findById(id).get());
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getLoggedInUser() {
        return null;
    }

    @Override
    public UserDto updateUserDetails(UpdateUserDto updateUser) {
        return userDtoMapper.mapToUserDto(userRepository.save(updateUserMapper.mapToUser(updateUser)));
    }

    @Override
    @Transactional
    public UserDto updateUser(Integer id, UpdateUserDto updateUser) {

        User user = userRepository.findById(id).get();
        if (userRepository.updateSomeFields(updateUser.getFirstName(), updateUser.getLastName(), updateUser.getPhone(), user.getId()) > 0) {
            return userDtoMapper.mapToUserDto(userRepository.findById(id).get());
        } else {
            throw new RuntimeException("Данные не изменились");
        }
    }

    @Override
    public void updateUser(User user) {
        userDtoMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto findByEmail(String email) {
        User findedUser = userRepository.findByEmail(email).orElseThrow(NotFoundException::new);
        return userDtoMapper.mapToUserDto(findedUser);
    }

    @Override
    public void updateUserAvatar(MultipartFile image, Authentication authentication) throws IOException {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(NotFoundException::new);
        Image newAvatar = imageService.saveInDataBase(image);

        user.setImageAddress(String.valueOf(newAvatar));
        user.setImageAddress("/images/" + newAvatar.getId());
        userRepository.save(user);
    }
}
