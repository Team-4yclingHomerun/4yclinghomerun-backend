package com.example.demo.player.controller;

import com.example.demo.player.dto.CatcherDetailResponse;
import com.example.demo.player.dto.PlayerResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;




/**
 * packageName    : com.example.demo.player.controller
 * fileName       : PlayerDataController
 * author         : JAEIK
 * date           : 10/31/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/31/24       JAEIK       최초 생성
 */
@RestController
@RequestMapping("/api/player")
public class PlayerDataController {

    @GetMapping("/catcherlist")
    public ResponseEntity<List<PlayerResponse>> getMockPlayerData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        InputStream inputStream = getClass().getResourceAsStream
                ("/data/player/catcherlist.json");
        // JSON 파일을 List<PlayerResponse>로 변환
        List<PlayerResponse> playerList = objectMapper.readValue(
                inputStream, new TypeReference<List<PlayerResponse>>() {
                });
        return ResponseEntity.ok(playerList);
    }

    @GetMapping("/catcherdetail")
    public ResponseEntity<CatcherDetailResponse> getMockCatcherDetail() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        InputStream inputStream = getClass().getResourceAsStream
                ("/data/player/catcherdetail/pcode=50066.json");

        CatcherDetailResponse catcherDetailResponse = objectMapper.readValue(
                inputStream, CatcherDetailResponse.class);

        return ResponseEntity.ok(catcherDetailResponse);
    }

    @GetMapping("/coachdetail")
    public ResponseEntity<JsonNode> getMockCoachDetail() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/player/coachdetail/pcode=89620.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }
}
