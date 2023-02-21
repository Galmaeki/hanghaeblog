package com.example.hanghaeblog.controller;

import com.example.hanghaeblog.dto.CommentsRequestDto;
import com.example.hanghaeblog.dto.PostRequestDto;
import com.example.hanghaeblog.dto.ResponseDto;
import com.example.hanghaeblog.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;
    @PostMapping("/comments/{id}")//포스트아이디
    public ResponseDto createComments(@PathVariable Long id, @RequestBody CommentsRequestDto commentsRequestDto, HttpServletRequest request) {
        ResponseDto<String> comment = new ResponseDto<>();
        comment.setData(commentsService.createComments(id, commentsRequestDto, request));
        comment.setSucess(comment.getData().equals("성공") ? "성공" : "실패");
        return comment;
    }

    @PutMapping("/comments/{id}")//댓글아이디
    public ResponseDto updatepost(@PathVariable Long id, @RequestBody CommentsRequestDto commentsRequestDto, HttpServletRequest request) {
        ResponseDto<String> comments = new ResponseDto<>();
        comments.setData(commentsService.update(id, commentsRequestDto, request));
        comments.setSucess(comments.getData().equals("성공") ? "성공" : "실패");
        return comments;
    }

    @DeleteMapping("/comments/{id}")
    public ResponseDto deletepost(@PathVariable Long id, HttpServletRequest request) {
        ResponseDto<String> comments = new ResponseDto<>();
        comments.setData(commentsService.delete(id, request));
        comments.setSucess(comments.getData().equals("성공") ? "성공" : "실패");
        return comments;
    }
}
