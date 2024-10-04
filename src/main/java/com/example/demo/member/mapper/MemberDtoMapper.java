package com.example.demo.member.mapper;

import com.example.demo.member.dto.MemberSignUpRequest;
import com.example.demo.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    Member toEntity(MemberSignUpRequest dto);
}
