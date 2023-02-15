package com.example.hanghaeblog.entity;


import com.example.hanghaeblog.dto.CommentsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comments extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "CommentsId")
    private Long Commentsid;

    @Column(nullable = false)
    private String Comments;
    //글 1개가 여러개의 댓글을 달 수 있으니 댓글은 매니투 원이 되어야함
    @Column(nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name="PostId")
    private Posts post;

    public Comments(CommentsRequestDto commentsDto,Posts post,String author) {
        Comments = commentsDto.getComments();
        this.author=author;
        this.post = post;
    }
}
