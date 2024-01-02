package ru.skypro.homework.dto.mapper;


import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.entities.User;
@Component
public class NewPasswordMapper {
    public NewPasswordDto mapToNewPasswordDto(User entity){
        NewPasswordDto dto = new NewPasswordDto();
        dto.setCurrentPassword(entity.getPassword());
        return dto;
    }

    public User mapToUser(NewPasswordDto dto){
        User entity = new User();
        entity.setPassword(dto.getNewPassword());
        return entity;
    }
}
