package com.example.demo.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * packageName    : com.example.demo.player.dto
 * fileName       : DataResponse
 * author         : JAEIK
 * date           : 10/31/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/31/24       JAEIK       최초 생성
 */
public record DataResponse(
        @JsonProperty("gameplayer")
        GamePlayer gamePlayerResponse,
        @JsonProperty("recentgamerecordlist")
        List<RecentGameResponse> recentGameResponse,
        @JsonProperty("recentgamerecordlistfutures")
        List<RecentGameRecordFuture> recentGameRecordFuture,
        @JsonProperty("seasonsummary")
        SeasonSummary seasonSummary,
        @JsonProperty("seasonsummaryfutures")
        SeasonSummaryFuture seasonSummaryFuture,
        @JsonProperty("yearrecordlist")
        List<YearRecord> yearRecord

) {
}
