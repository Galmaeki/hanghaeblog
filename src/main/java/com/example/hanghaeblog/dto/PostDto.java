package com.example.hanghaeblog.dto;

import com.example.hanghaeblog.entity.Posts;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto<T> {
    private String title;

    private String content;

    private String author;

    private T comments;
    private LocalDateTime modifiedAt;

    private Long id;
    public PostDto(Posts post, T t){
        this.author=post.getAuthor();
        this.title=post.getTitle();
        this.content=post.getContent();
        this.id=post.getId();
        this.modifiedAt=post.getModifiedAt();
        this.comments = t;
    }

    public PostDto(Posts post){
        this.author=post.getAuthor();
        this.title=post.getTitle();
        this.content=post.getContent();
        this.id=post.getId();
        this.modifiedAt=post.getModifiedAt();
    }
}
