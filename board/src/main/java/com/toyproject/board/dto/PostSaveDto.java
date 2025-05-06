package com.toyproject.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSaveDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
}
