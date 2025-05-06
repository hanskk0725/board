package com.toyproject.board.dto;

import com.toyproject.board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostListDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdDate;

    public PostListDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
    }
}
