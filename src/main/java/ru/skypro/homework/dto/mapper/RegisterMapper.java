package ru.skypro.homework.dto.mapper;


import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entities.User;


@Component
public class RegisterMapper {
    public RegisterDto mapToRegisterDto(User entity){
        RegisterDto dto = new RegisterDto();
        dto.setUsername(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setRole(entity.getRole());
        return dto;
    }

    public User mapToUser(RegisterDto dto){
        User entity = new User();
        entity.setEmail(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setRole(dto.getRole());
        return entity;
    }
}
