package com.ASD.twitty.Repository;

import com.ASD.twitty.Entities.Comment;
import com.ASD.twitty.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("SELECT c FROM Comment c WHERE c.id=:id")
    Optional<Comment> findCommentById(Long id);
}
