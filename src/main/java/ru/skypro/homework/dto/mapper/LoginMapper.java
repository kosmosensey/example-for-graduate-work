package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.entities.User;

@Component
public class LoginMapper {

    public User mapToUser(LoginDto dto) {
        User entity = new User();
        entity.setUsername(entity.getUsername());
        entity.setPassword(entity.getPassword());
        return entity;
    }

    public LoginDto entityToDto(User entity) {
        LoginDto dto = new LoginDto();
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
