package com.toyproject.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private String writer;

    public static Post createPost(String title, String content, String writer) {
        Post post = new Post();
        post.title = title;
        post.content = content;
        post.writer = writer;
        return post;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
