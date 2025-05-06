package com.toyproject.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;


    public static Post createPost(String title, String content, String writer) {
        Post post = new Post();
        post.title = title;
        post.content = content;
        post.writer = writer;
        post.updatedDate = null;
        return post;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedDate = LocalDateTime.now();
    }
}
