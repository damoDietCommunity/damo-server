package com.example.damoserver.post.entity;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.base.BaseTimeEntity;
import com.example.damoserver.comment.entity.Comment;
import com.example.damoserver.post.dto.request.UpdatePostRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false)
    private String title;

    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public Post(String title, String content, List<PostImage> images, Account account) {
        this.title = title;
        this.content = content;
        this.images = images;
        this.account = account;
    }

    public void update(UpdatePostRequest updatePostRequest) {
        this.title = updatePostRequest.title();
        this.content = updatePostRequest.content();
        this.images.clear();
        this.images.addAll(updatePostRequest.images().stream()
                .map(image -> PostImage.of(this, image.getImageUrl()))
                .toList());
    }
}
