package ru.skypro.homework.mapper.users;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entities.User;

@Mapper
public interface UserDtoMapper {
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    UserDto userEntityToDto(User user);

    User userDtoToEntity(UserDto dto);
}
