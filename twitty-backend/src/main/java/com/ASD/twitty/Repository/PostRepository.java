package com.ASD.twitty.Repository;

import com.ASD.twitty.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT p FROM Post p WHERE p.id=:id")
    Optional<Post> findPostById(Long id);
}
