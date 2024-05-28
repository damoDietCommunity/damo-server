package com.example.damoserver.comment.service;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.comment.dto.request.WriteCommentRequest;
import com.example.damoserver.comment.dto.response.CommentResponse;
import com.example.damoserver.comment.entity.Comment;
import com.example.damoserver.comment.repository.CommentRepository;
import com.example.damoserver.post.entity.Post;
import com.example.damoserver.post.repository.PostRepository;
import com.example.damoserver.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public List<CommentResponse> getComments(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post not found"));
        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse writeComment(WriteCommentRequest commentRequest, Long postId, Account account) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post not found"));
        Comment comment = commentRequest.toEntity(post,account);
        commentRepository.save(comment);

        return CommentResponse.from(comment);
    }

    @Transactional
    public void deleteComment(Long postId,Long commentId, Account account) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new RuntimeException("Post not found"));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("Comment not found"));

        if (!comment.getPost().equals(post)) {
            throw new RuntimeException("Comment not belong to this post");
        }

        if (!comment.getAccount().equals(account)) {
            throw new RuntimeException("Unauthorized to delete comment");
        }

        commentRepository.delete(comment);
    }
}
