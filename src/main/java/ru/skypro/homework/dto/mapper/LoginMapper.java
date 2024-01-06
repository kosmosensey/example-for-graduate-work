package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.entities.User;

@Component
public class LoginMapper {

    public User mapToUser(LoginDto dto) {
        User entity = new User();
        entity.setEmail(dto.getUsername());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public LoginDto entityToDto(User entity) {
        LoginDto dto = new LoginDto();
        dto.setUsername(entity.getEmail());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
