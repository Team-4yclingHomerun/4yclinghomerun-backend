package com.example.demo.member.entity;

import com.example.demo.jpa.support.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.Instant;

/**
 * packageName    : com.example.demo.member.entity
 * fileName       : Profile
 * author         : JAEIK
 * date           : 10/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/2/24        JAEIK       최초 생성
 */
@Entity
@Getter
@Table(schema = "profile")
public class Profile extends BaseEntity {
    private String nickname;
    private String phone;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
