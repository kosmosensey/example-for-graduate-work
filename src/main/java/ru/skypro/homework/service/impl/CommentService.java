package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.mapper.CommentMapper;
import ru.skypro.homework.dto.mapper.CreateOrUpdateCommentMapper;
import ru.skypro.homework.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CreateOrUpdateCommentMapper createOrUpdateCommentMapper;

    public List<CommentDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(CommentMapper::commentToCommentDto)
                .toList();
    }

    public CommentDto createComment(CommentDto commentDto) {
        return CommentMapper.commentToCommentDto(commentRepository.save(CommentMapper.commentDtoToComment(commentDto)));
    }
    public CommentDto createComment(CreateOrUpdateCommentDto commentDto) {
        return CommentMapper.commentToCommentDto(commentRepository.save(createOrUpdateCommentMapper.mapToComment(commentDto)));
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

    public List<CommentDto> getCommentsByAuthorId(Long id) {
        return commentRepository.getCommentsByAuthorId(id).stream()
                .map(CommentMapper::commentToCommentDto)
                .toList();
    }

}
