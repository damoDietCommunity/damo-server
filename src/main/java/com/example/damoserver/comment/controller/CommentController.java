package com.example.damoserver.comment.controller;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.comment.dto.request.CommentRequest;
import com.example.damoserver.comment.dto.request.WriteCommentRequest;
import com.example.damoserver.comment.dto.response.CommentResponse;
import com.example.damoserver.comment.entity.Comment;
import com.example.damoserver.comment.service.CommentService;
import com.example.damoserver.security.details.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long postId){
        //게시글 댓글 전부 조회. response로 받기
        return ResponseEntity.status(HttpStatus.OK)
                        .body(commentService.getComments(postId));
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponse> writeComment(
            @PathVariable Long postId,
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody WriteCommentRequest writeCommentRequest){

        Account account = principalDetails.getAccount();
        CommentResponse commentResponse = commentService.writeComment(writeCommentRequest,postId,account);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal PrincipalDetails principalDetails){

        Account account = principalDetails.getAccount();
        commentService.deleteComment(postId,commentId,account);

        return ResponseEntity.status(HttpStatus.OK)
                .body("댓글이 삭제되었습니다.");
    }

}
