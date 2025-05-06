package com.toyproject.board.service;

import com.toyproject.board.domain.Post;
import com.toyproject.board.repository.PostJpaRepository;
import com.toyproject.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

//    private final PostJpaRepository postJpaRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long addPost(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("게시물이 없습니다."));
    }

    public Page<Post> getPagedPosts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        return postRepository.findAll(pageRequest);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @Transactional
    public void updatePost(Long id, String title, String content) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("게시물이 없습니다."));
        post.updatePost(title, content);
        System.out.println("post.getLastModifiedDate() = " + post.getLastModifiedDate());
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("게시물이 없습니다."));

        postRepository.delete(post);
    }
}
