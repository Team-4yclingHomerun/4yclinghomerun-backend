package com.example.demo.member.repository;

import com.example.demo.member.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.example.demo.member.repository
 * fileName       : ProfileRepository
 * author         : JAEIK
 * date           : 10/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/2/24        JAEIK       최초 생성
 */
public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
