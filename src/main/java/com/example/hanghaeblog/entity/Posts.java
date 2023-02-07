package com.example.hanghaeblog.entity;

import com.example.hanghaeblog.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
public class Posts extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

    public Posts(PostRequestDto requestDto){//생성자 만듬
        this.title= requestDto.getTitle();
        this.content=requestDto.getContent();
        this.author=requestDto.getAuthor();
        this.password=requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto){//업데이트기능 미리 만들어둠
        this.title= requestDto.getTitle();
        this.content=requestDto.getContent();
        this.author=requestDto.getAuthor();
        this.password=requestDto.getPassword();
    }
}
