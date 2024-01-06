package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.dto.mapper.NewPasswordMapper;
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
    private final PasswordEncoder encoder;
    private final UpdateUserMapper updateUserMapper;
    private final NewPasswordMapper newPasswordMapper;


    @Override
    public void setPassword(String currentPassword, String newPassword, Authentication authentication) {
        if (authentication.getName() != null) {
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
            NewPasswordDto newPasswordDto = newPasswordMapper.mapToNewPasswordDto(user);
            if (encoder.matches(currentPassword, newPasswordDto.getCurrentPassword())) {
                user.setPassword(encoder.encode(newPassword));
                userRepository.save(user);
            }
        }
    }

    @Override
    public UserDto getLoggedInUser(Authentication authentication) {
        return userDtoMapper.mapToUserDto(userRepository.findByEmail(authentication.getName()).orElseThrow());
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
    public void updateUserAvatar(MultipartFile image, Authentication authentication) throws IOException {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        user.setData(image.getBytes());
        user.setImageUrl("/avatars/" + user.getId());
        userRepository.save(user);
    }

    @Override
    public byte[] getImage(Integer id) throws IOException {
        return userRepository.findById(id).get().getData();
    }

    @Override
    public UserDto findByEmail(String email) {
        User findedUser = userRepository.findByEmail(email).orElseThrow();
        return userDtoMapper.mapToUserDto(findedUser);
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByEmail(userName).orElseThrow();
    }
}
