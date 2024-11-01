package com.example.demo.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * packageName    : com.example.demo.player.dto
 * fileName       : CatcherDetailResponse
 * author         : JAEIK
 * date           : 11/1/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/1/24       JAEIK       최초 생성
 */
public record CatcherDetailResponse(
        @JsonProperty("data")
        DataResponse data
) {
}
