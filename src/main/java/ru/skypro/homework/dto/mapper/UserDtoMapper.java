package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entities.User;

@Component
public class UserDtoMapper {
    public UserDto mapToUserDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setRole(entity.getRole());
        dto.setImage("/image/" + entity.getImage().getId());
        return dto;
    }

    public User mapToUser(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setRole(dto.getRole());
        entity.setImageAddress(dto.getImage());
        return entity;
    }
}
