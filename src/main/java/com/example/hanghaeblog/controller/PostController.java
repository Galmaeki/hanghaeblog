package com.example.hanghaeblog.controller;

import com.example.hanghaeblog.dto.PostRequestDto;
import com.example.hanghaeblog.dto.PostResponseDto;
import com.example.hanghaeblog.entity.Posts;
import com.example.hanghaeblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public PostResponseDto getAll(){
        PostResponseDto<List> post = new PostResponseDto<>();
        post.setData(postService.getPosts());
        return post;
    }

    @PostMapping("/post")
    public String createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    @PutMapping("/post/{id}")
    public String updatepost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.update(id,postRequestDto);
    }

    @DeleteMapping("/post/{id}")
    public String deletepost(@PathVariable Long id,@RequestBody String pw){
        return postService.delete(id,pw);
    }

    @GetMapping("/post/{author}")
    public List<Posts> getone(@PathVariable String author){
        return postService.getauthorone(author);
    }


//    @GetMapping("/post/{id}")
//    public PostRequestDto getone(@PathVariable Long id){
//        return postService.getone(id);
//    }



}
