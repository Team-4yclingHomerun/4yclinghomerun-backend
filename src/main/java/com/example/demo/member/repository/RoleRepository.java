package com.example.demo.member.repository;

import com.example.demo.member.entity.Role;
import com.example.demo.member.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.example.demo.member.repository
 * fileName       : RoleRepository
 * author         : JAEIK
 * date           : 10/14/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/14/24        JAEIK       최초 생성
 */
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByRoleName(Role role);
}
