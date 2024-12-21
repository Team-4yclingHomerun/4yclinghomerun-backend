package com.example.demo.oauth.common.repository;

import com.example.demo.oauth.common.entity.OauthId;
import com.example.demo.oauth.common.entity.OauthMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * packageName    : com.example.demo.oauth
 * fileName       : OauthMemberRepository
 * author         : JAEIK
 * date           : 10/18/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/18/24        JAEIK       최초 생성
 */
public interface OauthMemberRepository extends JpaRepository<OauthMember, Long> {

    Optional<OauthMember> findByOauthId(OauthId oauthId);
    Optional<OauthMember> findById(UUID id);
}
