package com.example.damoserver.profile.entity;

import com.example.damoserver.account.entity.Account;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "nick_name", nullable = false, length = 50)
    private String nickName;

    @Column(name = "profile_image")
    private String profileImage;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    public Profile(String nickName, String profileImage, Account account) {
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.account = account;
    }
}
