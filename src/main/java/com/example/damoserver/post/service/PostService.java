package com.example.damoserver.post.service;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.post.dto.request.CreatePostRequest;
import com.example.damoserver.post.dto.request.UpdatePostRequest;
import com.example.damoserver.post.dto.response.PostResponse;
import com.example.damoserver.post.entity.Post;
import com.example.damoserver.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        return posts.stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponse createPost(CreatePostRequest createPostRequest, Account account) {
        Post post = Post.builder()
                .title(createPostRequest.title())
                .content(createPostRequest.content())
                .account(account)
                .imageUrls(createPostRequest.imageUrls())
                .build();

        postRepository.save(post);
        return PostResponse.from(post);
    }

    public PostResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new RuntimeException("Post not found"));

        return PostResponse.from(post);
    }

    @Transactional
    public PostResponse updatePost(Long postId, UpdatePostRequest updatePostRequest, Account account) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post not found"));

        if (!post.getAccount().equals(account)) {
            throw new RuntimeException("Unauthorized access");
        }

        post.update(updatePostRequest.title(), updatePostRequest.content(), updatePostRequest.imageUrls());

        return PostResponse.from(post);
    }

    @Transactional
    public void deletePost(Long postId, Account account) {
    Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post not found"));

        if (!post.getAccount().equals(account)) {
            throw new RuntimeException("Unauthorized access");
        }

        postRepository.delete(post);
    }
}
