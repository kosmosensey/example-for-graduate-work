package ru.skypro.homework.mapper.users;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.users.UpdateUser;
import ru.skypro.homework.entities.User;

@Mapper
public interface UpdateUserMapper {
    UpdateUserMapper INSTANCE = Mappers.getMapper(UpdateUserMapper.class);

    User updateUserToEntity(UpdateUser user);

    UpdateUser userEntityToUpdateUser(User entity);
}
