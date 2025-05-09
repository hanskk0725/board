package com.toyproject.board.repository;

import com.toyproject.board.dto.PostListDto;
import com.toyproject.board.dto.PostSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<PostListDto> search(PostSearchCond condition, Pageable pageable);
}
