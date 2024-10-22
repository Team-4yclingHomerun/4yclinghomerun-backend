package com.example.demo.email.entity;

import com.example.demo.jpa.support.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * packageName    : com.example.demo.email.entity
 * fileName       : VerifyEmailCode
 * author         : JAEIK
 * date           : 10/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/22/24        JAEIK       최초 생성
 */
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "auth", name = "verification_codes")
public class VerifyEmailCode extends BaseEntity {
    private String email;
    private String code;
    private Instant expiresTime;

}
