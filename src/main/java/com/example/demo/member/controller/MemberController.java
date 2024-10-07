package com.example.demo.member.controller;

import com.example.demo.member.dto.MemberSignInRequest;
import com.example.demo.member.dto.MemberSignUpRequest;
import com.example.demo.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
@RequestMapping("/yclinghomerun")
@Tag(name = "멤버", description = "Member API")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "사용자가 회원가입을 합니다.")
    @ApiResponse(responseCode = "400", description = "정규화에 맞추어서 입력하세요")
    @PostMapping("/signUp")
    ResponseEntity<?> createMember(@Valid @RequestBody MemberSignUpRequest request) {
        memberService.signUp(request);
        return ResponseEntity.ok(request);
    }
    @Operation(summary = "멤버 삭제", description = "UUID를 통해서 멤버를 삭제합니다.")
    @ApiResponse(responseCode = "400", description = "정규화에 맞추어서 입력하세요")
    @DeleteMapping("/deleteMember/{id}")
    ResponseEntity<?> deleteMember(@PathVariable UUID id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "로그인", description = "사용자가 로그인을 합니다.")
    @ApiResponse(responseCode = "400", description = "정규화에 맞추어서 입력하세요")
    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody MemberSignInRequest request) {
        memberService.signIn(request);
        return ResponseEntity.ok(request);
    }

}
