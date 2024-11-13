package com.example.demo.oauth.common.entity;


import com.example.demo.oauth.common.dto.OauthServerType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * packageName    : com.example.demo.oauth
 * fileName       : OauthId
 * author         : JAEIK
 * date           : 10/18/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/18/24        JAEIK       최초 생성
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class OauthId {
    @Column(name = "oauth_server_id")
    private String oauthServerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "oauth_server")
    private OauthServerType oauthServerType;

}

