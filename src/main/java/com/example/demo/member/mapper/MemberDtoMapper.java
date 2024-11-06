package com.example.demo.member.mapper;

import com.example.demo.member.dto.MemberSignInRequest;
import com.example.demo.member.dto.MemberSignUpRequest;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberStatus;
import com.example.demo.member.entity.Roles;
import org.mapstruct.Mapper;


import java.time.Instant;


/**
 * packageName    : com.example.demo.member.mapper
 * fileName       : MemberDtoMapper
 * author         : JAEIK
 * date           : 10/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/3/24        JAEIK       최초 생성
 */

@Mapper(componentModel = "spring")
public interface MemberDtoMapper {
    Member toEntity(MemberSignUpRequest dto, MemberStatus status, Instant createdAt, Instant updateAt, Roles role);

    Member signInToEntity(MemberSignInRequest signInRequest);
}
