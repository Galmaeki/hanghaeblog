package com.example.hanghaeblog.controller;

import com.example.hanghaeblog.dto.PostDto;
import com.example.hanghaeblog.dto.PostRequestDto;
import com.example.hanghaeblog.dto.PostResponseDto;
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
        if(postService.getPosts().isEmpty())
        {
            post.setData(null);
            post.setSucess("글이 업서오!");
        }else{
            post.setData(postService.getPosts());
            post.setSucess("성공");
        }
        return post;
    }

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        PostResponseDto<String> post = new PostResponseDto<>();
        post.setData(postService.createPost(requestDto));
        post.setSucess("작성 성공");
        return post;
    }

    @PutMapping("/post/{id}")
    public PostResponseDto updatepost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        PostResponseDto<String> post = new PostResponseDto<>();
        post.setData(postService.update(id,postRequestDto));
        post.setSucess(post.getData().equals("실패")?"실패":"성공");
        return post;
    }

    @DeleteMapping("/post/{id}")
    public PostResponseDto deletepost(@PathVariable Long id,@RequestBody PostRequestDto postRequestDto){
        PostResponseDto<String> post = new PostResponseDto<>();
        post.setData(postService.delete(id,postRequestDto));
        post.setSucess(post.getData().equals("실패")?"실패":"성공");
        return post;
    }

    @GetMapping("/post/author/{author}")
    public PostResponseDto getauthorone(@PathVariable String author){
        PostResponseDto<List> post = new PostResponseDto<>();
        post.setSucess("성공");
        post.setData(postService.getauthorone(author));
        return post;
    }


    @GetMapping("/post/{id}")
    public PostResponseDto getidone(@PathVariable Long id){
        PostResponseDto<PostDto> post = new PostResponseDto<>();
        post.setSucess("성공");
        post.setData(postService.getidone(id));
        return post;
    }



}
