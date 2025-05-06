package com.toyproject.board.service;

import com.toyproject.board.domain.Post;
import com.toyproject.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long addPost(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public Post getPost(Long id) {
        return postRepository.findOne(id);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @Transactional
    public void updatePost(Long id, String title, String content) {
        Post post = postRepository.findOne(id);
        post.updatePost(title, content);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.delete(id);
    }
}
