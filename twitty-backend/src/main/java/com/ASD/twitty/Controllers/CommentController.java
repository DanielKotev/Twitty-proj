package com.ASD.twitty.Controllers;

import com.ASD.twitty.Beans.CommentRequest;
import com.ASD.twitty.Entities.Comment;
import com.ASD.twitty.Entities.Post;
import com.ASD.twitty.Entities.User;
import com.ASD.twitty.Repository.CommentRepository;
import com.ASD.twitty.Repository.PostRepository;
import com.ASD.twitty.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/all")
    public List<Comment> getPosts(){ return commentRepository.findAll(); }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrEdit(@RequestBody CommentRequest form)
    {

        boolean isNew = form.getId() == null;
        Comment comments = new Comment(form.getId(),
                form.getContent(),
                userRepository.findById(form.getUserId()).get(),
                postRepository.findPostById(form.getPostId()).get());

        Comment comment = commentRepository.save(comments);
        Map<String,Object> response = new HashMap<>();
        response.put("generatedId",comment.getId());
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
    public ResponseEntity<?> deleteComment(@RequestParam Long id)
    {
        commentRepository.findCommentById(id).ifPresent(comment -> commentRepository.delete(comment));
        return ResponseEntity.ok("Успешно изтрит");
    }
}
