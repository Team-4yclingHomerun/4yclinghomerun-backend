package com.example.demo.member.controller;

import com.example.demo.member.dto.MemberSignUpRequest;
import com.example.demo.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/demo")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signUp")
    ResponseEntity<?> createMember(@Valid @RequestBody MemberSignUpRequest request) {
        memberService.signUp(request);
        return ResponseEntity.ok(request);

    }

}
