package com.toyproject.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSearchCond {

    private String title;
    private String writer;

}
