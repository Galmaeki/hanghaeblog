package com.example.hanghaeblog.controller;

import com.example.hanghaeblog.dto.PostDto;
import com.example.hanghaeblog.dto.PostRequestDto;
import com.example.hanghaeblog.dto.ResponseDto;
import com.example.hanghaeblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public ResponseDto getAll(){
        ResponseDto<List> post = new ResponseDto<>();
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
    public ResponseDto createPost(@RequestBody PostRequestDto requestDto){
        ResponseDto<String> post = new ResponseDto<>();
        post.setData(postService.createPost(requestDto));
        post.setSucess("작성 성공");
        return post;
    }

    @PutMapping("/post/{id}")
    public ResponseDto updatepost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        ResponseDto<String> post = new ResponseDto<>();
        post.setData(postService.update(id,postRequestDto));
        if(post.getData()==null){
            post.setSucess("id가 잘못되었습니다");
        }else{
        post.setSucess(post.getData().equals("실패")?"실패":"성공");}
        return post;
    }

    @DeleteMapping("/post/{id}")
    public ResponseDto deletepost(@PathVariable Long id,@RequestBody PostRequestDto postRequestDto){
        ResponseDto<String> post = new ResponseDto<>();
        post.setData(postService.delete(id,postRequestDto));
        if(post.getData()==null){
            post.setSucess("id가 잘못되었습니다");
        }else{
            post.setSucess(post.getData().equals("실패")?"실패":"성공");}
        return post;
    }

    @GetMapping("/post/author/{author}")
    public ResponseDto getauthorone(@PathVariable String author){
        ResponseDto<List> post = new ResponseDto<>();
        post.setData(postService.getauthorone(author));
        post.setSucess(post.getData().isEmpty()?"글이 업서오!":"성공");
        return post;
    }


    @GetMapping("/post/{id}")
    public ResponseDto getidone(@PathVariable Long id){
        ResponseDto<PostDto> post = new ResponseDto<>();
        post.setData(postService.getidone(id));
        post.setSucess(post.getData()==null?"글이 업서오!":"성공");
        return post;
    }



}
