package com.example.demo.player.dto;

/**
 * packageName    : com.example.demo.player.dto
 * fileName       : RecentGameResponse
 * author         : JAEIK
 * date           : 10/31/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/31/24       JAEIK       최초 생성
 */
public record RecentGameResponse(
        int ab,
        int bb,
        String bra,
        int cs,
        String displayDate,
        int gd,
        int h2,
        int h3,
        int hit,
        int hp,
        int hr,
        String hra,
        int kk,
        String matchTeamCode,
        String matchTeamName,
        int rbi,
        int run,
        int sb
) {
}
