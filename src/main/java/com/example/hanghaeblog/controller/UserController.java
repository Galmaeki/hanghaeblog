package com.example.hanghaeblog.controller;

import com.example.hanghaeblog.dto.ResponseDto;
import com.example.hanghaeblog.dto.UsersRequestDto;
import com.example.hanghaeblog.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UsersService usersService;

    @PostMapping("/join")
    public ResponseDto join(@RequestBody @Valid UsersRequestDto usersRequestDto){
    ResponseDto<String> post = new ResponseDto();
    post.setData(usersService.join(usersRequestDto));
    if(post.getData().equals("성공"))
        post.setSucess("성공");
    else{
        post.setSucess("실패");
    }
    return post;
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseDto join(@RequestBody UsersRequestDto usersRequestDto, HttpServletResponse response){
        ResponseDto<String> post = new ResponseDto();
        post.setData(usersService.login(usersRequestDto,response));
        if(post.getData().equals("성공"))
            post.setSucess("성공");
        else{
            post.setSucess("실패");
        }
        return post;
    }

}
