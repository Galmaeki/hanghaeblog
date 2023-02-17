package com.example.hanghaeblog.controller;

import com.example.hanghaeblog.dto.CommentsRequestDto;
import com.example.hanghaeblog.dto.ResponseDto;
import com.example.hanghaeblog.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;
    @PostMapping("/comments/{id}")
    public ResponseDto createComments(@PathVariable Long id, @RequestBody CommentsRequestDto commentsRequestDto, HttpServletRequest request) {
        ResponseDto<String> comment = new ResponseDto<>();
        comment.setData(commentsService.createComments(id, commentsRequestDto, request));
        comment.setSucess(comment.getData().equals("성공") ? "성공" : "실패");
        return comment;
    }



}
