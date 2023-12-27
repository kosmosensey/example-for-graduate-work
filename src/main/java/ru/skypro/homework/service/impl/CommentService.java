package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.mapper.CommentMapper;
import ru.skypro.homework.entities.Ad;
import ru.skypro.homework.entities.Comment;
import ru.skypro.homework.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentDto createComment(CommentDto commentDto) {
        return CommentMapper.commentToCommentDto(commentRepository.save(CommentMapper.commentDtoToComment(commentDto)));
    }

    public CommentDto updateComment(CommentDto commentDto) {
        return CommentMapper.commentToCommentDto(commentRepository.save(CommentMapper.commentDtoToComment(commentDto)));
    }

    public CommentDto findComment(Long id) {
        return CommentMapper.commentToCommentDto(commentRepository.findById(id).get());
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
