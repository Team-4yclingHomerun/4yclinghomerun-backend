package com.example.demo.oauth.common;

import com.example.demo.oauth.common.dto.OauthServerType;
import com.example.demo.oauth.common.entity.OauthMember;

/**
 * packageName    : com.example.demo.oauth
 * fileName       : OauthMemberClient
 * author         : JAEIK
 * date           : 10/18/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/18/24        JAEIK       최초 생성
 */
public interface OauthMemberClient {

    OauthServerType supportServer();

    OauthMember fetch(String code);
}
