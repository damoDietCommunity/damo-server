package com.example.damoserver.post.entity;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.base.BaseTimeEntity;
import com.example.damoserver.comment.entity.Comment;
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

    /*
    값타입 컬렉션. 처음 써봄. onetomany로 엔티티를 컬렉션으로 사용하는
     것이 아닌, 임베디드 타입 같은 것을 컬렉션으로 사용.
     RDB에 컬렉션을 담을 구조가 없어서 별도의 테이블을 만들어서 저장.
     개념적으로 1 대 다. 안쓰고 그냥 엔티티 하나 파는게 더 좋음.
     나중에 오류나면 고치기.
    */
    @ElementCollection
    @CollectionTable
    @Column(name = "image_url")
    @Size(max = 10)
    private List<String> imageUrls= new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public Post(String title, String content, List<String> imageUrls, Account account) {
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
        this.account = account;
    }

    public void update(String title, String content, List<String> imageUrls) {
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
    }
}
