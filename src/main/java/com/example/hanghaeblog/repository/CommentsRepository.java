package com.example.hanghaeblog.repository;

import com.example.hanghaeblog.entity.Comments;
import com.example.hanghaeblog.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    //List<Comments> findAllByOrderByCreatedAt();
    //List<Comments> findByPostOrderByCreatedAt(Posts posts);
    List<Comments> findByPostsOrderByCreatedAt(Posts posts);

}
