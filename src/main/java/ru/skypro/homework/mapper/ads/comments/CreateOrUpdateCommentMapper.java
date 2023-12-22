package ru.skypro.homework.mapper.ads.comments;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.comments.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entities.Comment;

@Mapper
public interface CreateOrUpdateCommentMapper {
    CreateOrUpdateCommentMapper INSTANCE = Mappers.getMapper(CreateOrUpdateCommentMapper.class);

    CreateOrUpdateCommentDTO entityCommentToCreateOrUpdateCommentDTO(Comment entity);

    Comment createOrUpdateCommentDTOToEntityComment(CreateOrUpdateCommentDTO dto);
}
