package com.example.hanghaeblog.dto;

import com.example.hanghaeblog.entity.Posts;
import lombok.Getter;

@Getter
public class PostDto {
    private String title;

    private String content;

    private String author;
    public PostDto(Posts post){
        this.author=post.getAuthor();
        this.title=post.getTitle();
        this.content=post.getContent();
    }
}
