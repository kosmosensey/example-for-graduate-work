package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entities.Comment;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from comments where author_id = ?", nativeQuery = true)
    public List<Comment> getCommentsByAuthorId(Long id);

    List<Comment> findByAdPk(Integer id);

    Comment findById(Integer id);
}
