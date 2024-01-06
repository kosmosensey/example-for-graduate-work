package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.dto.mapper.UpdateUserMapper;
import ru.skypro.homework.dto.mapper.UserDtoMapper;
import ru.skypro.homework.entities.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final UpdateUserMapper updateUserMapper;


    @Override
    public UserDto getLoggedInUser(Authentication authentication) {
        return userDtoMapper.mapToUserDto(userRepository.findByEmail(authentication.getName()).orElseThrow());
    }

    @Override
    public UserDto updateUserDetails(UpdateUserDto updateUser) {
        return userDtoMapper.mapToUserDto(userRepository.save(updateUserMapper.mapToUser(updateUser)));
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setPhone(updateUserDto.getPhone());
        userRepository.save(user);
        return updateUserMapper.mapToUpdateUserDto(user);
    }

    @Override
    public UserDto findByEmail(String email) {
        User findedUser = userRepository.findByEmail(email).orElseThrow();
        return userDtoMapper.mapToUserDto(findedUser);
    }

    @Override
    public void updateUserAvatar(MultipartFile image, Authentication authentication) throws IOException {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        user.setData(image.getBytes());
        user.setImageUrl("/avatar/" + user.getId());
        userRepository.save(user);
    }

    @Override
    public byte[] getImage(Integer id) throws IOException {
        return userRepository.findById(id).get().getData();
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByEmail(userName).orElseThrow();
    }
}
