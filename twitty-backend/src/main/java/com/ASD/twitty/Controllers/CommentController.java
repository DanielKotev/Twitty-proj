package com.ASD.twitty.Controllers;

import com.ASD.twitty.Entities.Comment;
import com.ASD.twitty.Entities.Post;
import com.ASD.twitty.Entities.User;
import com.ASD.twitty.Repository.CommentRepository;
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

    @GetMapping("/all")
    public List<Comment> getPosts(){ return commentRepository.findAll(); }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrEdit(@RequestParam(required = false) Long id,
                                          @RequestParam(required = false) String content,
                                          @RequestParam(required = false) User user,
                                          @RequestParam(required = false) Post post)
    {
        boolean isNew = id==null;
        Comment comments = new Comment(id,content,user,post);

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
    public ResponseEntity<?> deleteComment(@RequestParam(required = false) Long id)
    {
        commentRepository.findCommentById(id).ifPresent(comment -> commentRepository.delete(comment));
        return ResponseEntity.ok("Успешно изтрит");
    }
}
