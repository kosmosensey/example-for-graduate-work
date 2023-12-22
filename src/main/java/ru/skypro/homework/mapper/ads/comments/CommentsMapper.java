package ru.skypro.homework.mapper.ads.comments;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.comments.CommentDTO;
import ru.skypro.homework.dto.ads.comments.CommentsDTO;
import ru.skypro.homework.entities.Comment;

import java.util.List;

@Mapper
public interface CommentsMapper {
    CommentsMapper INSTANCE = Mappers.getMapper(CommentsMapper.class);

    CommentsDTO commentsToCommentsDto(List<Comment> comments);

    List<CommentDTO> mapComments(List<Comment> comments);
}

