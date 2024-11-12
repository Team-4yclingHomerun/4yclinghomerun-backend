package com.example.demo.member.controller;

import com.example.demo.auth.AuthenticateMember;
import com.example.demo.member.dto.MemberSignInRequest;
import com.example.demo.member.dto.MemberSignInResponse;
import com.example.demo.member.dto.MemberSignUpRequest;
import com.example.demo.member.dto.NicknameCheckRequest;
import com.example.demo.member.dto.NicknameCheckResponse;
import com.example.demo.member.dto.UsernameCheckRequest;
import com.example.demo.member.dto.UsernameCheckResponse;
import com.example.demo.member.entity.MemberStatus;
import com.example.demo.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

import static com.example.demo.auth.AuthorizationConstants.LOGIN_MEMBER_ATTRIBUTE;

/**
 * packageName    : com.example.demo.member.controller
 * fileName       : MemberController
 * author         : JAEIK
 * date           : 10/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/4/24        JAEIK       최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "멤버", description = "Member API")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "사용자가 회원가입을 합니다.")
    @ApiResponse(responseCode = "400", description = "형식에 맞추어서 입력하세요")
    @PostMapping("/signUp")
    ResponseEntity<?> createMember(@Valid @RequestBody MemberSignUpRequest request) {
        MemberStatus status = MemberStatus.ACTIVE;
        Instant createAt = Instant.now();
        Instant updateAt = createAt;
        memberService.signUp(request, status, createAt, updateAt);
        return ResponseEntity.ok(request);
    }

    @Operation(summary = "멤버 삭제", description = "UUID를 통해서 멤버를 삭제합니다.")
    @ApiResponse(responseCode = "400", description = "형식에 맞추어서 입력하세요")
    @DeleteMapping("/me")
    ResponseEntity<?> deleteMember(@RequestAttribute(name = LOGIN_MEMBER_ATTRIBUTE) AuthenticateMember member) {
        UUID id = member.id();
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "로그인", description = "사용자가 로그인을 합니다.")
    @ApiResponse(responseCode = "400", description = "형식에 맞추어서 입력하세요")
    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody MemberSignInRequest request) {
        MemberSignInResponse signInResponse =memberService.signIn(request);
        return ResponseEntity.ok(signInResponse);
    }
    @Operation(summary = "아이디 중복체크", description = "사용자가 아이디 중복체크 합니다.")
    @PostMapping("/verify-username")
    ResponseEntity<?> checkUsername(@Valid @RequestBody UsernameCheckRequest request) {
        UsernameCheckResponse response = memberService.checkUsernameAvailability(request);
        return ResponseEntity.ok().body(response);
    }
    @Operation(summary = "닉네임 중복체크", description = "사용자가 닉네임을 중복체크 합니다.")
    @PostMapping("/verify-nickname")
    ResponseEntity<?> checkNickname(@Valid @RequestBody NicknameCheckRequest request) {
        NicknameCheckResponse response = memberService.checkNicknameAvailability(request);
        return ResponseEntity.ok().body(response);
    }
}
