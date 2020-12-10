package com.ASD.twitty.Entities;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column
    @JoinColumn(name="user_id")
    private Long user_id;

    @Column
    @JoinColumn(name="post_id")
    private Long post_id;

    public Comment(Long id, String content, Long user_id, Long post_id) {
        this.id = id;
        this.content = content;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }
}
