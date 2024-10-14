package com.example.demo.member.entity;

import me.letsdev.util.enums.EnumUtil;

/**
 * packageName    : com.example.demo.member.entity
 * fileName       : Role
 * author         : JAEIK
 * date           : 10/9/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/9/24        JAEIK       최초 생성
 */
public enum Role {
    USER(0b0000_0001),
    ADMIN(0b1000_0000);

    private final int bit;

    // enum 클래스는 독특하게도, 열거 상수의 생성자가 먼저 실행되고 static 블록을 실행합니다.
    static {
        assert EnumUtil.isUnique(Role.class, Role::bit)
                : "Role의 모든 bit 필드가 고유해야 합니다.";
    }

    Role(int bit) {
        this.bit = bit;
    }

    public int bit() {
        return bit;
    }
}
