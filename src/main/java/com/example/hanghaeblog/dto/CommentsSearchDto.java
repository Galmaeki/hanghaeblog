package com.example.hanghaeblog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentsSearchDto<T> {
    private T t;
}
