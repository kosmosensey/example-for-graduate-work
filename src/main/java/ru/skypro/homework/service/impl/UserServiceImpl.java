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

import ru.skypro.homework.exception.UserNotFoundException;
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

    /**
     Sets the password for the user.
     @param currentPassword The current password of the user.
     @param newPassword The new password to be set.
     @param authentication The authentication object representing the current user.
     */
    @Override
    public void setPassword(String currentPassword, String newPassword, Authentication authentication) {
        if (authentication.getName() != null) {
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);
            NewPasswordDto newPasswordDto = newPasswordMapper.mapToNewPasswordDto(user);
            if (encoder.matches(currentPassword, newPasswordDto.getCurrentPassword())) {
                user.setPassword(encoder.encode(newPassword));
                userRepository.save(user);
            }
        }
    }
    /**
     Retrieves the UserDto object representing the logged-in user.
     @param authentication The authentication object representing the current user.
     @return The UserDto object representing the logged-in user.
     @throws UserNotFoundException if the user is not found.
     */
    @Override
    public UserDto getLoggedInUser(Authentication authentication) {
        return userDtoMapper.mapToUserDto(userRepository.findByEmail(authentication.getName())
                .orElseThrow(UserNotFoundException::new));
    }
    /**
     Updates the user information.
     @param updateUserDto The UpdateUserDto object containing the updated user information.
     @param authentication The authentication object representing the current user.
     @return The UpdateUserDto object representing the updated user information.
     @throws UserNotFoundException if the user is not found.
     */
    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setPhone(updateUserDto.getPhone());
        userRepository.save(user);
        return updateUserMapper.mapToUpdateUserDto(user);
    }
    /**
     Updates the user avatar.
     @param image The MultipartFile object representing the new user avatar image.
     @param authentication The authentication object representing the current user.
     @throws IOException if an I/O error occurs while reading the image data.
     @throws UserNotFoundException if the user is not found.
     */
    @Override
    public void updateUserAvatar(MultipartFile image, Authentication authentication) throws IOException {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new);
        user.setData(image.getBytes());
        user.setImageUrl("/avatars/" + user.getId());
        userRepository.save(user);
    }
    /**
     Retrieves the image data of the user with the specified ID.
     @param id The ID of the user.
     @return The byte array representing the image data.
     @throws IOException if an I/O error occurs while reading the image data.
     */
    @Override
    public byte[] getImage(Integer id) throws IOException {
        return userRepository.findById(id).get().getData();
    }
    /**
     Retrieves the UserDto object for the user with the specified email.
     @param email The email of the user.
     @return The UserDto object representing the user.
     @throws UserNotFoundException if the user is not found.
     */
    @Override
    public UserDto findByEmail(String email) {
        User findedUser = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return userDtoMapper.mapToUserDto(findedUser);
    }
    /**
     Retrieves the User object for the user with the specified username.
     @param userName The username of the user.
     @return The User object representing the user.
     @throws UserNotFoundException if the user is not found.
     */
    @Override
    public User getUser(String userName) {
        return userRepository.findByEmail(userName).orElseThrow(UserNotFoundException::new);
    }
}
