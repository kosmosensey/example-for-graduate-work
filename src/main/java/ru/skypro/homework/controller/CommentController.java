package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.impl.AdServiceImpl;
import ru.skypro.homework.service.impl.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")

public class CommentController {
    private final CommentService commentService;
    private final AdServiceImpl adService;

    //Получение комментариев объявления
    @GetMapping("/{id}/comments")

    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("id") Long id) {
        if (!commentService.getCommentsByAuthorId(id).isEmpty()) {
            return new ResponseEntity<>(commentService.getCommentsByAuthorId(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Добавление комментария к объявлению
    @PostMapping ("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable("id") Integer id,
                                                 @RequestBody CreateOrUpdateCommentDto comment) {
        if (adService.findExtendedAd(id) != null && comment != null) {
            return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // удаление комментария
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") Integer adId,
                                              @PathVariable("commentId") Long commentId) {
        if (adService.findExtendedAd(adId) != null && commentService.findComment(commentId) != null) {
            commentService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // Обновление комментария
//    Коммент от Мавиле: Не поняла энпоинт

    @PatchMapping ("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("adId") Long adId,
                                                    @PathVariable("commentId") Long commentId){
        CommentDto updatedComment = new CommentDto();
        return ResponseEntity.ok(updatedComment);
        }
    }



