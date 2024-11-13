package com.example.demo.oauth.common.authcode;

import com.example.demo.oauth.common.dto.OauthServerType;

/**
 * packageName    : com.example.demo.oauth
 * fileName       : AuthCodeRequestUrlProvier
 * author         : JAEIK
 * date           : 10/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/17/24        JAEIK       최초 생성
 */
public interface AuthCodeRequestUrlProvider {
    OauthServerType supportServer();

    String provide();
}
