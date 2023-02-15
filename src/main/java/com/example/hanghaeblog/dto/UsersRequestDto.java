package com.example.hanghaeblog.dto;

import com.example.hanghaeblog.entity.UsersEnum;
import lombok.Getter;

@Getter
public class UsersRequestDto {
    private String username;
    private String password;

    private String role;
}
