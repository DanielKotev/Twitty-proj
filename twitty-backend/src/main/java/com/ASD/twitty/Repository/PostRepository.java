package com.ASD.twitty.Repository;

import com.ASD.twitty.Entities.Comment;
import com.ASD.twitty.Entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT p FROM Post p WHERE p.id=:id")
    Optional<Post> findPostById(Long id);

    @Query("SELECT p " +
            "FROM Post p " +
            "WHERE p.content LIKE CONCAT('%',:content,'%')")
    Page<Post> findPostByName(Pageable pageable,@Param("content") String content);

    @Query("SELECT c " +
            "FROM Comment c " +
            "JOIN c.post p " +
            "JOIN c.user u "+
            "WHERE p.id=:id AND u.active=TRUE "+
            "ORDER BY c.id DESC")
    Set<Comment> findCommentsOfPosts(Long id);
}
