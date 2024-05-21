package com.example.damoserver.post.controller;


import com.example.damoserver.account.entity.Account;
import com.example.damoserver.post.dto.request.CreatePostRequest;
import com.example.damoserver.post.dto.request.UpdatePostRequest;
import com.example.damoserver.post.dto.response.PostResponse;
import com.example.damoserver.post.service.PostService;
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
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        List<PostResponse> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId){
        PostResponse postResponse = postService.getPostById(postId);

        return ResponseEntity.status(HttpStatus.OK).body(postResponse);
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody CreatePostRequest createPostRequest){
        Account account = principalDetails.getAccount();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(createPostRequest, account));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody UpdatePostRequest updatePostRequest
    ) {
        Account account = principalDetails.getAccount();
        return ResponseEntity.status(HttpStatus.OK)
                        .body(postService.updatePost(postId, updatePostRequest, account));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        Account account = principalDetails.getAccount();
        postService.deletePost(postId, account);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("deleted");
    }

}
