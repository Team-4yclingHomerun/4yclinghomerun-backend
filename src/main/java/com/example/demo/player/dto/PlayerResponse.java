package com.example.demo.player.dto;

/**
 * packageName    : com.example.demo.player.dto
 * fileName       : PlayerResponse
 * author         : JAEIK
 * date           : 10/31/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/31/24       JAEIK       최초 생성
 */
public record PlayerResponse(
        String backnum,
        int energybar,
        String energybarName,
        String gyear,
        String hasFanpage,
        String hittype,
        String mobilePlayerImg,
        String mobilePlayerImg1,
        String mobilePlayerImg2,
        String pcode,
        String playerName,
        String playerPrvwImg,
        String position,
        int rank,
        String rankName,
        String teamName
) {
}
