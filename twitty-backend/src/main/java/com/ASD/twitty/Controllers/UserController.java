package com.ASD.twitty.Controllers;

import com.ASD.twitty.Entities.Post;
import com.ASD.twitty.Entities.User;
import com.ASD.twitty.Repository.PostRepository;
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
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrUpdate(@RequestParam(required = false) Long id,
                                          @RequestParam(required = false) String username,
                                          @RequestParam(required = false) String password)
    {
        boolean isNew = id==null;
        User users = new User(id,username,password);

        User user = userRepository.save(users);
        Map<String,Object> response = new HashMap<>();
        response.put("generatedId",user.getId());
        if(isNew)
        {
            response.put("message","Успешно добавен");
        }
        else {
            response.put("message","Успешно редактиран");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/followedPosts")
    public ResponseEntity<?> getPostsOfFollowedUsers(@RequestParam Long id) {

        Set<Post> result = userRepository.fingPostsOfFollowedUsers(id);

        return !result.isEmpty() ?
                ResponseEntity.ok().body(result) :
                ResponseEntity.ok().body("No posts found");

    }
}