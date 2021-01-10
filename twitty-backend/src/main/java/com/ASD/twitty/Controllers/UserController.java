package com.ASD.twitty.Controllers;

import com.ASD.twitty.Entities.Comment;
import com.ASD.twitty.Entities.Post;
import com.ASD.twitty.Entities.User;
import com.ASD.twitty.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("getById")
    public Optional<User> getUserById(@RequestParam(required = false) Long id) {
        if (id == null) {
            return null;
        }
        return userRepository.findById(id);
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

    @PostMapping("/deactivateUser")
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

    @GetMapping("/followedPosts")
    public ResponseEntity<?> getPostsOfFollowedUsers(@RequestParam(defaultValue = "1") int currentPage,
                                                         @RequestParam(defaultValue = "5") int perPage,
                                                         @RequestParam Long id) {

        Pageable pageable = PageRequest.of(currentPage - 1, perPage);
        Page<Post> result = userRepository.fingPostsOfFollowedUsers(pageable, id);

        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        response.put("posts", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalElements", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> ownPosts(@RequestParam(defaultValue = "1") int currentPage,
                                                     @RequestParam(defaultValue = "5") int perPage,
                                                     @RequestParam Long id) {

        Pageable pageable = PageRequest.of(currentPage - 1, perPage);
        Page<Post> result = userRepository.fingPostsOfUser(pageable, id);

        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        response.put("posts", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalElements", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return ResponseEntity.ok().body(response);
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

    @GetMapping("/isFollowing")
    public ResponseEntity<?> isFollowing(@RequestParam Long followerId, @RequestParam Long followedId) {

        Boolean isFollowing = !userRepository.isFollowing(followerId, followedId).isEmpty();

        Map<String, Object> response = new HashMap<>();
        response.put("isFollowing", isFollowing);

        return ResponseEntity.ok().body(response);
    }
}