package com.ASD.twitty.Repository;

import com.ASD.twitty.Entities.Comment;
import com.ASD.twitty.Entities.Post;
import com.ASD.twitty.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u Where u.username=:username")
     Optional<User> findUserByUsername(String username);

    @Query("SELECT u from User u Where u.username=:username AND u.password=:password")
    Optional<User> LoginCheckAuth(String username, String password);

    @Query("SELECT u from User u Where u.username=:username AND u.active=TRUE")
    Optional<User> findActiveUser(String username);

    @Query("SELECT p " +
            "FROM Post p " +
            "WHERE p.user.id = :id OR p.user.id IN " +
            "(SELECT f.id FROM User u JOIN u.following f WHERE u.id = :id) " +
            "ORDER BY p.id DESC")
    Page<Post> fingPostsOfFollowedUsers(Pageable pageable, Long id);

    @Query("SELECT p FROM User u JOIN u.posts p WHERE u.id = :id ORDER BY p.id DESC")
    Page<Post> fingPostsOfUser(Pageable pageable, Long id);

    @Query("SELECT u FROM User u JOIN u.following f WHERE (u.id = :followerId and f.id = :followedId)")
    Set<User> isFollowing(Long followerId, Long followedId);
}
