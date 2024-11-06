package com.example.demo.oauth;


import com.example.demo.jpa.support.UuidBaseEntity;
import com.example.demo.member.entity.MemberStatus;
import com.example.demo.member.entity.Roles;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * packageName    : com.example.demo.oauth
 * fileName       : OauthMember
 * author         : JAEIK
 * date           : 10/18/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/18/24        JAEIK       최초 생성
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "oauth_member", schema = "auth")
public class OauthMember extends UuidBaseEntity {
    @Embedded
    private OauthId oauthId;
    private String nickname;
    private String email;
    private String profileImageUrl;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            schema = "auth",
            name = "oauth_member_roles",
            joinColumns = @JoinColumn(name = "oauth_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    public OauthMember(OauthId oauthId, String nickname, String email, String profileImageUrl, Roles role) {
        this.oauthId = oauthId;
        this.nickname = nickname;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.roles = Set.of(role);
        this.status = MemberStatus.ACTIVE;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void addRole(Roles role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }
}
