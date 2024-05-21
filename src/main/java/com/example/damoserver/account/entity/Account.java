package com.example.damoserver.account.entity;

import com.example.damoserver.account.dto.request.CreateAccountRequest;
import com.example.damoserver.comment.entity.Comment;
import com.example.damoserver.post.entity.Post;
import com.example.damoserver.profile.entity.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(nullable = false, unique = true)
    private String name; //계정의 로그인 아이디

    @Column(nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    //chat message 넣기

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Account(String name, String password, Role role, Profile profile, String email,List<Post> posts, List<Comment> comments) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.profile = profile;
        this.posts = posts;
        this.comments = comments;
    }

    public static Account from(CreateAccountRequest request) {
        return Account.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .role(Role.ROLE_USER) //user로 고정
                .build();
    }
}