package com.example.damoserver.comment.repository;

import com.example.damoserver.comment.entity.Comment;
import com.example.damoserver.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
