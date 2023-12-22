package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ru.skypro.homework.dto.ads.comments.*;

@RestController
@RequestMapping("/ads")

public class CommentController {

    //Получение комментариев объявления
    @GetMapping("/{id}/comments")

    public ResponseEntity<CommentsDTO> getComments(@PathVariable("id") Integer id) {
        CommentsDTO comments = null;// получение комментариев объявления по id
        if (comments != null) {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Добавление комментария к объявлению
    @PostMapping ("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable("id") Integer id,
                                                 @RequestBody CreateOrUpdateCommentDTO comment) {
        if (id != null && comment != null) { // проверка
            CommentDTO newComment = null; //  добавление комментария
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
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("adId") int adId,
                                                    @PathVariable("commentId") int commentId){
        CommentDTO updatedComment = new CommentDTO();
        return ResponseEntity.ok(updatedComment);

        }
    }



