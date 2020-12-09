package com.ASD.twitty.Controllers;

import com.ASD.twitty.Entities.User;
import com.ASD.twitty.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
            boolean isNew = id == null;
            Map<String, Object> response = new HashMap<>();
            User users = new User(id, username, password);
            User user = userRepository.save(users);

            response.put("generatedId", user.getId());
            if (isNew) {
                response.put("message", "Успешно добавен");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}