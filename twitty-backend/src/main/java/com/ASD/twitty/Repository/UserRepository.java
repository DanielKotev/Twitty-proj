package com.ASD.twitty.Repository;

import com.ASD.twitty.Entities.Post;
import com.ASD.twitty.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u Where u.username=:username")
     Optional<User> findUserByUsername(String username);

    @Query("SELECT u from User u Where u.username=:username AND u.active=TRUE")
    Optional<User> findActiveUser(String username);

    @Query("SELECT p " +
            "FROM User u " +
            "JOIN u.following f " +
            "JOIN f.posts p " +
            "WHERE u.id = :id OR p.user.id = :id" +
            " ORDER BY p.id DESC")
    Set<Post> fingPostsOfFollowedUsers(Long id);

    @Query("SELECT c " +
            "FROM Comment c " +
            "JOIN c.post p " +
            "JOIN c.user u "+
            "WHERE p.id=:id AND u.active=TRUE "+
            "ORDER BY c.id DESC")
    Set<Comment> findCommentsOfPosts(Long id);
}
