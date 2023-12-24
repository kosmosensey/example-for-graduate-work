package ru.skypro.homework.dto.mapper;


import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entities.Comment;


@Component
public class CommentMapper {
    public static Comment commentDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setPk(commentDto.getPk());
        comment.setAuthor(commentDto.getAuthor());
        comment.setAuthorImage(commentDto.getAuthorImage());
        comment.setAuthorFirstName(commentDto.getAuthorFirstName());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setText(commentDto.getText());
        return comment;
    }

    public static CommentDto commentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(comment.getPk());
        commentDto.setAuthor(comment.getAuthor());
        commentDto.setAuthorImage(comment.getAuthorImage());
        commentDto.setAuthorFirstName(comment.getAuthorFirstName());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setText(comment.getText());
        return commentDto;
    }
}
