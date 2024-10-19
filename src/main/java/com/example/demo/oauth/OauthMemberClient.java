package com.example.demo.oauth;

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
