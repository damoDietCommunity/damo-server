package com.example.damoserver.auth.repository;

import com.example.damoserver.auth.entity.EmailVerification;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface EmailVerificationRepository extends KeyValueRepository<EmailVerification, String> {

}
