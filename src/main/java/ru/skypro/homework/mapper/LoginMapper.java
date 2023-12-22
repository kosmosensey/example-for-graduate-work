package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.entities.User;

@Mapper
public interface LoginMapper {

    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);

    Login loginEntityToDto(User user);

    User loginDtoToEntity(Login login);
}
