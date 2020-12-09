package com.ASD.twitty.Controllers;

import com.ASD.twitty.Entities.Post;
import com.ASD.twitty.Entities.User;
import com.ASD.twitty.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostRepository postRepository;

    @GetMapping("/all")
    public List<Post> getPosts(){ return postRepository.findAll(); }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrUpdate(@RequestParam(required = false) Long id,
                                          @RequestParam(required = false) String content,
                                          @RequestParam(required = false) Long user_id)
    {
        boolean isNew = id==null;
        Post posts = new Post(id,content,user_id);

        Post post = postRepository.save(posts);
        Map<String,Object> response = new HashMap<>();
        response.put("generatedId",post.getId());
        if(isNew)
        {
            response.put("message","Успешно добавен");
        }
        else {
            response.put("message","Успешно редактиран");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePost(@RequestParam Long id)
    {
        postRepository.findPostById(id).ifPresent(post ->postRepository.delete(post));
        return ResponseEntity.ok("Успешно изтрит");
    }
}
