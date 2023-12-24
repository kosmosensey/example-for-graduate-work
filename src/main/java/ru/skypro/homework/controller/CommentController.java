package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

@RestController
@RequestMapping("/ads")

public class CommentController {

    //Получение комментариев объявления
    @GetMapping("/{id}/comments")

    public ResponseEntity<CommentsDto> getComments(@PathVariable("id") Integer id) {
        CommentsDto comments = null;// получение комментариев объявления по id
        if (comments != null) {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Добавление комментария к объявлению
    @PostMapping ("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable("id") Integer id,
                                                 @RequestBody CreateOrUpdateCommentDto comment) {
        if (id != null && comment != null) { // проверка
            CommentDto newComment = null; //  добавление комментария
            return new ResponseEntity<>(newComment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    // удаление комментария
@DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") int adId,
                                              @PathVariable("commentId") int commentId) {
// commentService.deleteComment(commentId);
    return ResponseEntity.ok().build();

    }

// Обновление комментария

    @PatchMapping ("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("adId") int adId,
                                                    @PathVariable("commentId") int commentId){
        CommentDto updatedComment = new CommentDto();
        return ResponseEntity.ok(updatedComment);

        }
    }



