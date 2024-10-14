package com.example.demo.jpa.support;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * packageName    : com.example.demo.jpa.support
 * fileName       : MemberRolesKeyEntity
 * author         : JAEIK
 * date           : 10/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/13/24        JAEIK       최초 생성
 */
@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRolesKeyEntity implements Serializable {
    private UUID memberId;
    private Long roleId;
}
