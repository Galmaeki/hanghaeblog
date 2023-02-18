package com.example.hanghaeblog.dto;

import com.example.hanghaeblog.entity.Comments;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentsDto {
    private String comments;

    private String author;

    public CommentsDto(Comments comments){
        this.comments=comments.getComments();
        this.author = comments.getAuthor();
    }

}
