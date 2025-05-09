package com.toyproject.board.repository;

import com.toyproject.board.domain.Post;
import com.toyproject.board.dto.PostListDto;
import com.toyproject.board.dto.PostSearchCond;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostRepositoryImplTest {

    @Autowired PostRepository repository;
    @Autowired EntityManager em;

    @Test
    void search(){

        em.persist(Post.createPost("new_title1", "content_test1", "new_writer1"));
        em.persist(Post.createPost("new_title2", "content_test2", "new_writer2"));

        PostSearchCond condition = new PostSearchCond();
        condition.setTitle("new_title");

        PageRequest pageRequest = PageRequest.of(0, 1);

        Page<PostListDto> search = repository.search(condition, pageRequest);
        List<PostListDto> content = search.getContent();
        assertThat(content).extracting("writer").containsExactly("new_writer1", "new_writer2");
        for (PostListDto postListDto : content) {
            System.out.println("postListDto = " + postListDto);
        }
    }

}