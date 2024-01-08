package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.mapper.CommentMapper;
import ru.skypro.homework.entities.Ad;
import ru.skypro.homework.entities.Comment;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;

    /**
     Retrieves the comments for an ad.
     @param id The ID of the ad.
     @return The CommentsDto object containing the list of comments.
     */
    @Override
    public CommentsDto getCommentsAds(Integer id) {
        List<Comment> comment = commentRepository.findByAdPk(id);
        if (comment == null) {
            return null;
        }
        List<CommentDto> commentList = comment.stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
        return new CommentsDto(commentList.size(), commentList);
    }

    /**
     Creates a new comment for an ad.
     @param id The ID of the ad.
     @param createCommentDto The CreateOrUpdateCommentDto object containing the comment details.
     @param authentication The authentication object representing the current user.
     @return The CommentDto object representing the created comment.
     */
    @Override
    public CommentDto createComment(Integer id,
                                    CreateOrUpdateCommentDto createCommentDto,
                                    Authentication authentication) {
        Timestamp localDateTime = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        Comment comment = new Comment();
        Ad ad = adRepository.findAdByPk(id);
        if (ad == null) {
            return null;
        }
        comment.setText(createCommentDto.getText());
        comment.setCreatedAt(localDateTime);
        comment.setAd(ad);
        comment.setUser(userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new));
        commentRepository.save(comment);
        return commentMapper.commentToCommentDto(comment);
    }
    /**
     Deletes a comment.
     @param adId The ID of the ad.
     @param commentId The ID of the comment.
     */
    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        Comment comment = commentRepository.findById(commentId);
        commentRepository.delete(comment);
    }
    /**
     Updates a comment.
     @param adId The ID of the ad.
     @param commentId The ID of the comment.
     @param createCommentDto The CreateOrUpdateCommentDto object containing the updated comment details.
     @return The CommentDto object representing the updated comment.
     */
    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createCommentDto) {
        Comment comment = commentRepository.findById(commentId);
        if (comment != null) {
            comment.setText(createCommentDto.getText());
            commentRepository.save(comment);
            return commentMapper.commentToCommentDto(comment);
        }
        return null;
    }

}
