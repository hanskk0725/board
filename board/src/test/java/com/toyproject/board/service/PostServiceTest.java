package com.toyproject.board.service;

import com.toyproject.board.domain.Post;
import com.toyproject.board.repository.PostJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired private PostService postService;
    @Autowired private PostJpaRepository postJpaRepository;

    @Test
    @Rollback(false)
    void savePost(){
        String title = "title";
        String content = "content";
        String writer = "writer";

        Post post = Post.createPost(title, content, writer);
        postJpaRepository.save(post);

        Post findPost = postJpaRepository.findOne(post.getId());

        assertThat(post.getId()).isEqualTo(findPost.getId());
    }

    @Test
    @Rollback(value = false)
    void updatePost(){
        Post findPost = postJpaRepository.findOne(1L);

        String content = "updatedPost";
        findPost.updatePost(findPost.getTitle(), content);


        assertThat(findPost.getContent()).isEqualTo(content);
        assertThat(findPost.getCreatedDate()).isNotEqualTo(findPost.getUpdatedDate());
    }

    @Test
    @Rollback(false)
    void deletePost(){
        postJpaRepository.findOne(1L);
        postService.deletePost(1L);
        assertThat(postJpaRepository.findOne(1L)).isNull();
    }
}