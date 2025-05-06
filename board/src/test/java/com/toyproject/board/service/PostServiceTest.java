package com.toyproject.board.service;

import com.toyproject.board.domain.Post;
import com.toyproject.board.repository.PostRepository;
import org.assertj.core.api.Assertions;
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
    @Autowired private PostRepository postRepository;

    @Test
    @Rollback(false)
    void savePost(){
        String title = "title";
        String content = "content";
        String writer = "writer";

        Post post = Post.createPost(title, content, writer);
        postRepository.save(post);

        Post findPost = postRepository.findOne(post.getId());

        assertThat(post.getId()).isEqualTo(findPost.getId());
    }

    @Test
    @Rollback(value = false)
    void updatePost(){
        Post findPost = postRepository.findOne(1L);

        String content = "updatedPost";
        findPost.updatePost(findPost.getTitle(), content);


        assertThat(findPost.getContent()).isEqualTo(content);
        assertThat(findPost.getCreatedDate()).isNotEqualTo(findPost.getUpdatedDate());
    }

    @Test
    @Rollback(false)
    void deletePost(){
        postRepository.findOne(1L);
        postService.deletePost(1L);
        assertThat(postRepository.findOne(1L)).isNull();
    }
}