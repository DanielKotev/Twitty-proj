package com.ASD.twitty.Repository;

import com.ASD.twitty.Entities.Post;
import com.ASD.twitty.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT p FROM User u JOIN u.following f JOIN f.posts p WHERE u.id = :id OR p.user.id = :id")
    Set<Post> fingPostsOfFollowedUsers(Long id);
}
