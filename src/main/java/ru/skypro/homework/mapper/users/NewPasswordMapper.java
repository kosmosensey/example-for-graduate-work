package ru.skypro.homework.mapper.users;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entities.User;
@Mapper
public interface NewPasswordMapper {
    NewPasswordMapper INSTANCE = Mappers.getMapper(NewPasswordMapper.class);

    User userDtoToEntity(UserDto dto);

    UserDto userEntityToDto(User user);
}
