package com.example.hanghaeblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto<T> {
    private String sucess;
    private T data;
}
