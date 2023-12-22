package ru.skypro.homework.mapper.ads.comments;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.comments.CommentDTO;
import ru.skypro.homework.entities.Comment;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDTO commentToCommentDTO(Comment entity);

    Comment commentDTOToComment(CommentDTO dto);
}

