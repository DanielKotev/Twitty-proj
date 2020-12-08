package com.ASD.twitty.Repository;

import com.ASD.twitty.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
