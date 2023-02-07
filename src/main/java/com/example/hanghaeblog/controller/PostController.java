package com.example.hanghaeblog.controller;

import com.example.hanghaeblog.dto.PostRequestDto;
import com.example.hanghaeblog.entity.Posts;
import com.example.hanghaeblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public List<Posts> getAll(){
        return postService.getPosts();
    }

    @PostMapping("/post")
    public Posts createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);

    }

    @PutMapping("/post/{id}")
    public Long updatepost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.update(id,postRequestDto);
    }

    @DeleteMapping("/post/{id}")
    public Long deletepost(@PathVariable Long id){
        return postService.delete(id);
    }





}
