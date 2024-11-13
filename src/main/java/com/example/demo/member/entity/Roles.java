package com.example.demo.member.entity;

import com.example.demo.jpa.support.BaseEntity;
import com.example.demo.oauth.common.entity.OauthMember;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * packageName    : com.example.demo.member.entity
 * fileName       : Roles
 * author         : JAEIK
 * date           : 10/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/13/24        JAEIK       최초 생성
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "auth")
public class Roles extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private Role roleName;
    @ManyToMany(mappedBy = "roles")
    private Set<Member> members = new HashSet<>();
    @ManyToMany
    private Set<OauthMember> oauthMembers = new HashSet<>();

    public Roles(Role roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return super.getId();
    }

}

