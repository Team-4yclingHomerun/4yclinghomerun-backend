package com.example.demo.oauth.service;

import com.example.demo.exception.SignInErrorCode;
import com.example.demo.jwt.JwtToken;
import com.example.demo.member.entity.Role;
import com.example.demo.member.entity.Roles;
import com.example.demo.member.repository.RoleRepository;
import com.example.demo.member.service.JwtAuthenticationProxyService;
import com.example.demo.oauth.OauthMember;
import com.example.demo.oauth.OauthMemberClientComposite;
import com.example.demo.oauth.OauthMemberRepository;
import com.example.demo.oauth.OauthServerType;
import com.example.demo.oauth.authcode.AuthCodeRequestUrlProviderComposite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * packageName    : com.example.demo.oauth
 * fileName       : OauthService
 * author         : JAEIK
 * date           : 10/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/17/24        JAEIK       최초 생성
 */
@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final RoleRepository roleRepository;
    private final JwtAuthenticationProxyService jwtProvider;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public JwtToken login(OauthServerType oauthServerType, String authCode) {
        // Oauth 서버 사용자 정보 토큰 가져오기
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        //OauthMember 사용자 찾기 (존재하지않으면  새로 저장)
        OauthMember newOauthmember = oauthMemberRepository.findByOauthId(oauthMember.getOauthId())
                .orElseGet(() -> {
                    Roles userRole = roleRepository.findByRoleName(Role.USER);
                    if (userRole == null) {
                        throw new IllegalArgumentException("권한을 찾을 수 없습니다.");
                    }
                    oauthMember.addRole(userRole);

                    return oauthMemberRepository.save(oauthMember);
                });

        if (newOauthmember.getRoles().isEmpty()) {
            throw SignInErrorCode.NO_ROLE_FOUND_MEMBER.defaultException();
        }
        Role role = null;
        for (var item : newOauthmember.getRoles()) {
            role = item.getRoleName();
        }
        return jwtProvider.createJwtToken(
                newOauthmember.getId(),
                newOauthmember.getNickname(),
                role
        );
    }
}


