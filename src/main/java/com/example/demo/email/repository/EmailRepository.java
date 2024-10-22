package com.example.demo.email.repository;

import com.example.demo.email.entity.VerifyEmailCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : com.example.demo.email
 * fileName       : emailRepository
 * author         : JAEIK
 * date           : 10/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/22/24        JAEIK       최초 생성
 */
public interface EmailRepository extends JpaRepository<VerifyEmailCode, Long> {
    Optional<VerifyEmailCode> findByEmailAndCode(String email, String code);
}
