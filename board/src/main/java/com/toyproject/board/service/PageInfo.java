package com.toyproject.board.service;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageInfo {
    private int startPage;
    private int endPage;
    private boolean hasPrev;
    private boolean hasNext;
    private int currentPage;
    private int totalPages;

    public PageInfo(Page<?> page) {
        int barSize = 10;
        currentPage = page.getNumber();
        totalPages = page.getTotalPages();

        int currentBlock = currentPage / barSize;
        startPage = currentBlock * barSize;
        endPage = Math.min(startPage + barSize - 1, totalPages - 1);

        hasPrev = startPage > 0;
        hasNext = endPage < totalPages -1;
    }
}
