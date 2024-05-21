package com.example.damoserver.profile.repository;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Boolean existsByAccount(Account account);
    Profile findByAccount(Account account);
}
