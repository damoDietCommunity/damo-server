package com.example.damoserver.auth.service;

import com.example.damoserver.auth.entity.EmailVerification;
import com.example.damoserver.auth.repository.EmailVerificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EmailVerificationService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailService emailService;
    private final Long EXPIRATION_TIME;
    private final Integer VERIFICATION_CODE_LENGTH;

    public EmailVerificationService(
            EmailVerificationRepository emailVerificationRepository,
            EmailService emailService,
            @Value("${spring.verification-code.expire-time}") Long expirationTime,
            @Value("${spring.verification-code.length}") Integer verificationCodeLength) {
        this.emailVerificationRepository = emailVerificationRepository;
        this.emailService = emailService;
        this.EXPIRATION_TIME = expirationTime;
        this.VERIFICATION_CODE_LENGTH = verificationCodeLength;
    }

    @Transactional
    public void sendEmailVerificationCode(String email) {
        if (emailVerificationRepository.findById(email).isPresent()) {
            emailVerificationRepository.deleteById(email);
        }
        //랜덤 코드 생성 후 save
        String code = emailService.createCode();
        //이메일 전송
        emailService.sendEmail(email, code);
        //redis에 코드 저장
        EmailVerification emailVerification = new EmailVerification(email, code, EXPIRATION_TIME);
        emailVerificationRepository.save(emailVerification);
    }

    @Transactional
    public void verifyCode(String email, String code) {
        EmailVerification emailVerification = emailVerificationRepository.findById(email).orElseThrow(() ->
                new RuntimeException("Email verification not found"));
        if (!emailVerification.verify(code)) {
            throw new RuntimeException("Invalid verification code");
        }
        emailVerificationRepository.delete(emailVerification);
    }
}
