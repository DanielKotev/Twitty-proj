package com.ASD.twitty.Controllers;

import com.ASD.twitty.Entities.Comment;
import com.ASD.twitty.Entities.Post;
import com.ASD.twitty.Entities.User;
import com.ASD.twitty.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getActiveUsers()
    {
        return userRepository.findAll();
    }

    public Optional<User> CheckUserName(@RequestParam String userName){ return userRepository.findUserByUsername(userName); }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrUpdate(@RequestParam(required = false) Long id,
                                          @RequestParam() String username,
                                          @RequestParam() String password)
    {
        if(CheckUserName(username).isPresent()) {
            return ResponseEntity.ok().body("Username is taken ");
        }
       else{
            Map<String, Object> response = new HashMap<>();
            User users = new User(id, username, password,true);
            User user = userRepository.save(users);

            response.put("generatedId", user.getId());
                response.put("message", "Успешно добавен");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/DeactivateUser")
    public ResponseEntity<?> deactivateUser(@RequestParam() String username)
    {
        if(userRepository.findActiveUser(username).isPresent())
        {
            User users=userRepository.findActiveUser(username).get();
            users.setActive(false);
            User user = userRepository.save(users);

            return ResponseEntity.ok().body("User is Deactivated");
        }
        else {
            User users=userRepository.findUserByUsername(username).get();
            users.setActive(true);
            User user = userRepository.save(users);
            return ResponseEntity.ok().body("User is active");
        }

    }


    @GetMapping("/commentsPost")
    public ResponseEntity<?> getCommentsOfPosts(@RequestParam Long id) {

        Set<Comment> result = userRepository.findCommentsOfPosts(id);

        return !result.isEmpty() ?
                ResponseEntity.ok().body(result) :
                ResponseEntity.ok().body("No Comments Found");

    }

    @GetMapping("/followedPosts")
    public ResponseEntity<?> getPostsOfFollowedUsers(@RequestParam Long id) {

        Set<Post> result = userRepository.fingPostsOfFollowedUsers(id);

        return !result.isEmpty() ?
                ResponseEntity.ok().body(result) :
                ResponseEntity.ok().body("No posts found");

    }

    @PostMapping("/follow")
    public ResponseEntity<?> follow(@RequestParam Long followerId, @RequestParam Long followedId) {

        Optional<User> followed = userRepository.findById(followedId);
        Optional<User> follower = userRepository.findById(followerId);

        if (!followed.isPresent() || !follower.isPresent()) {
            return ResponseEntity.ok().body("Invalid id!");
        }

        follower.get().getFollowing().add(followed.get());
        userRepository.save(follower.get());
        return ResponseEntity.ok().body("User successfully followed!");
    }

    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollow(@RequestParam Long followerId, @RequestParam Long followedId) {

        Optional<User> followed = userRepository.findById(followedId);
        Optional<User> follower = userRepository.findById(followerId);

        if (!followed.isPresent() || !follower.isPresent()) {
            return ResponseEntity.ok().body("Invalid id!");
        }

        follower.get().getFollowing().remove(followed.get());
        userRepository.save(follower.get());
        return ResponseEntity.ok().body("User successfuly unfollowed!");
    }
}