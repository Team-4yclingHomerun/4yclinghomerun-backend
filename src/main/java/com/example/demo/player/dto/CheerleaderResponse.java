package com.example.demo.player.dto;

/**
 * packageName    : com.example.demo.player.dto
 * fileName       : CheeleaderResponse
 * author         : JAEIK
 * date           : 10/31/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/31/24       JAEIK       최초 생성
 */
public record CheerleaderResponse(
        String delYn,
        String imgPath,
        String imgPrvwPath,
        String leaderBirthDay,
        String leaderBloodGroups,
        String leaderCareer,
        String leaderEngName,
        String leaderGreeting,
        String leaderHeight,
        String leaderHobby,
        String leaderLikePlayer,
        String leaderMotto,
        String leaderName,
        String leaderNickName,
        String leaderPosition,
        int leaderSeq,
        String leaderType,
        String listImgPath,
        long regDttm,
        String regr,
        String snsId,
        String snsMemberId,
        String thumbOffImgPath,
        String thumbOnImgPath,
        String titleImgPath,
        long updDttm,
        String updr,
        String webviewDetailImgPath,
        String webviewListImgPath
) {
}
