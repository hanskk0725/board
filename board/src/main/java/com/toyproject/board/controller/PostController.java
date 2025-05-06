package com.toyproject.board.controller;

import com.toyproject.board.domain.Post;
import com.toyproject.board.dto.PostListDto;
import com.toyproject.board.dto.PostSaveDto;
import com.toyproject.board.service.PageInfo;
import com.toyproject.board.service.PostService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

//    @GetMapping("/posts")
    public String allPosts(Model model) {
        List<Post> allPosts = postService.getAllPosts();
        List<PostListDto> listDto = allPosts.stream()
                .map(p -> new PostListDto(p))
                .toList();
        model.addAttribute("posts", listDto);
        return "posts/list";
    }

    //페이징
    @GetMapping("/posts")
    public String pagedPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Page<Post> pagedPosts = postService.getPagedPosts(page, size);
        Page<PostListDto> postDto = pagedPosts.map(p -> new PostListDto(p));

        PageInfo pageInfo = new PageInfo(postDto);

        model.addAttribute("posts", postDto);
        model.addAttribute("pageInfo", pageInfo);
        return "posts/list";
    }

    @GetMapping("/posts/new")
    public String addPostForm(@ModelAttribute("post") PostSaveDto dto) {
        return "posts/form";
    }

    @PostMapping("/posts/new")
    public String savePost(@ModelAttribute("post") PostSaveDto dto) {
        Post post = Post.createPost(dto.getTitle(), dto.getContent(), dto.getWriter());
        postService.addPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String post(@PathVariable Long id, Model model,
                       @RequestParam(value = "post", required = false) Post post) {
        Post findPost = postService.getPost(id);
        model.addAttribute("post", findPost);

        return "posts/post";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = postService.getPost(id);
        PostSaveDto dto = new PostSaveDto(post.getId(), post.getTitle(), post.getContent(), post.getWriter());

        model.addAttribute("post", dto);
        return "posts/updatePostForm";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable Long id, @ModelAttribute("post") PostSaveDto dto) {
        postService.updatePost(id, dto.getTitle(), dto.getContent());
        return "redirect:/posts/" + id;
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

    //더미 데이터
    @PostConstruct
    public void init() {
        for (int i = 0; i < 10000; i++) {
            Post post = Post.createPost("title" + i, "content" + i, "writer" + i);
            postService.addPost(post);
        }
    }
}
