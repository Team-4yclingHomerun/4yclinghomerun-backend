package com.example.demo.member.entity;

import com.example.demo.jpa.support.UuidBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.beans.Encoder;
import java.time.Instant;

/**
 * packageName    : com.example.demo.member.entity
 * fileName       : Member
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(schema = "auth")
public class Member extends UuidBaseEntity {
    private String username;
    private String password;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    public void updatePassword(String password) {
        this.password = password;
    }
}


// C -> struct -> group of vars
//  abc.a
//  abc.b
//  abc.c

// C++ -> class (struct including function)