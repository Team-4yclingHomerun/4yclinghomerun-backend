package com.example.demo.oauth.google.dto;

import com.example.demo.oauth.common.entity.OauthId;
import com.example.demo.oauth.common.entity.OauthMember;
import com.example.demo.oauth.common.dto.OauthServerType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * packageName    : com.example.demo.oauth.google
 * fileName       : GoogleMemberResponse
 * author         : JAEIK
 * date           : 10/20/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/20/24        JAEIK       최초 생성
 */
@JsonNaming(value = SnakeCaseStrategy.class)
public record GoogleMemberResponse(
        String sub,
        String name,
        String givenName,
        String familyName,
        String picture,
        String email,
        boolean emailVerified,
        String locale
) {
    public OauthMember toDomain() {
        return OauthMember.builder()
                .oauthId(new OauthId(sub, OauthServerType.GOOGLE))
                .nickname(name)
                .email(email)
                .profileImageUrl(picture)
                .build();
    }
}
