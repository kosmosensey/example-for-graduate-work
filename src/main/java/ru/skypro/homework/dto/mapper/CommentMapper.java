package ru.skypro.homework.dto.mapper;


import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entities.Comment;


@Component
public class CommentMapper {
    public static Comment commentDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setText(commentDto.getText());
        return comment;
    }

    public static CommentDto commentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setAuthor(comment.getUser().getId());
        commentDto.setAuthorImage("/avatars/" + comment.getUser().getId());
        commentDto.setAuthorFirstName(comment.getUser().getFirstName());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setText(comment.getText());
        return commentDto;
    }
}
