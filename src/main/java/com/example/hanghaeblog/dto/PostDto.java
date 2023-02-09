package com.example.hanghaeblog.dto;

import com.example.hanghaeblog.entity.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    private String title;

    private String content;

    private String author;
    private LocalDateTime modifiedAt;

    private Long id;
    public PostDto(Posts post){
        this.author=post.getAuthor();
        this.title=post.getTitle();
        this.content=post.getContent();
        this.id=post.getId();
        this.modifiedAt=post.getModifiedAt();
    }
}
