package com.example.demo.member.entity;

import com.example.demo.jpa.support.UuidBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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
    private String email;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            schema = "auth",
            name = "member_roles",
            joinColumns =@JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    public Member(String username, String password, String nickname, String email, Roles role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.status = MemberStatus.ACTIVE;
        this.roles = Set.of(role);
    }


    public void addRole(Roles role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void setToDeletedMember() {
        this.status = MemberStatus.REMOVED;
    }
}


// C -> struct -> group of vars
//  abc.a
//  abc.b
//  abc.c

// C++ -> class (struct including function)