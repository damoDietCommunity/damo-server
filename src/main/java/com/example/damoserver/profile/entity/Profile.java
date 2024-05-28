package com.example.damoserver.profile.entity;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.profile.dto.request.EditProfileRequest;
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

    //별명
    @Column(name = "nick_name", nullable = false, length = 50)
    private String nickName;

    //프로필이미지
    @Column(name = "profile_image")
    private String profileImage;

    //기본 이미지 상수
    private static final String DEFAULT_PROFILE_IMAGE_URL = "/path/to/default.png"; // 실제 기본 이미지 URL로 변경

    //프로필의 계정
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    public Profile(String nickName, String profileImage, Account account) {
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.account = account;
    }

    public void update(EditProfileRequest editProfileRequest) {
        this.nickName = editProfileRequest.nickName();
        this.profileImage = editProfileRequest.profileImage();
    }


    public static String getDefaultProfileImageUrl() {
        return DEFAULT_PROFILE_IMAGE_URL;
    }
}
