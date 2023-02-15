package com.example.hanghaeblog.repository;

import com.example.hanghaeblog.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Long> {

}
