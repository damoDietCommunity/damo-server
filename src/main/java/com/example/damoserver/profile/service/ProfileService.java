package com.example.damoserver.profile.service;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.account.repository.AccountRepository;
import com.example.damoserver.profile.dto.request.EditProfileRequest;
import com.example.damoserver.profile.entity.Profile;
import com.example.damoserver.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    //프로필 생성
    @Transactional
    public Profile editProfile(Account account ,EditProfileRequest editProfileRequest) {

        if (isProfileExistByAccount(account)) {
            Profile profile = profileRepository.findByAccount(account);
            profile.update(editProfileRequest);
            //return 값 response 만들기
            return profile;
        }

        return Profile.builder()
                .profileImage(editProfileRequest.profileImage())
                .nickName(editProfileRequest.nickName())
                .build();
    }

    public boolean isProfileExistByAccount(Account account) {
        return profileRepository.existsByAccount(account);
    }
}
