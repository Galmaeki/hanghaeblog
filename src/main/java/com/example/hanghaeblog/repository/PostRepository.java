package com.example.hanghaeblog.repository;

import com.example.hanghaeblog.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Posts, Long> {//포스트와 id
    List<Posts> findAllByOrderByModifiedAtDesc();
    List<Posts> findByAuthor(String author);


}
