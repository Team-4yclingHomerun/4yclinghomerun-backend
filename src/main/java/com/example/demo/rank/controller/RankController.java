package com.example.demo.rank.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

/**
 * packageName    : com.example.demo.rank.controller
 * fileName       : RankController
 * author         : JAEIK
 * date           : 11/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/4/24       JAEIK       최초 생성
 */
@RestController
@Tag(name = "Json데이터:rank")
@RequestMapping("/api/game/rank")
public class RankController {

    @GetMapping("/batter/hr/top3")
    public ResponseEntity<JsonNode> getMockBatterHr() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/batter/hr/top3.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/batter/hra/top3")
    public ResponseEntity<JsonNode> getMockBatterHra() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/batter/hra/top3.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/batter/total/top5")
    public ResponseEntity<JsonNode> getMockBatterTotal() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/batter/total/top5.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/crowd/gyear-2019")
    public ResponseEntity<JsonNode> getMockCrowd2019() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/crowd/gyear=2019.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/crowd/gyear-2020")
    public ResponseEntity<JsonNode> getMockCrowd2020() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/crowd/gyear=2020.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/crowd/gyear-2021")
    public ResponseEntity<JsonNode> getMockCrowd2021() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/crowd/gyear=2021.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/crowd/gyear-2022")
    public ResponseEntity<JsonNode> getMockCrowd2022() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/crowd/gyear=2022.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/crowd/gyear-2023")
    public ResponseEntity<JsonNode> getMockCrowd2023() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/crowd/gyear=2023.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/crowd/gyear-2024")
    public ResponseEntity<JsonNode> getMockCrowd2024() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/crowd/gyear=2024.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/kt/batter")
    public ResponseEntity<JsonNode> getMockKtBatter() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/kt/batter.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/kt/pitcher")
    public ResponseEntity<JsonNode> getMockKtPitcher() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/kt/pitcher.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/pitcher/era/top3")
    public ResponseEntity<JsonNode> getMockPitcherEra() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/pitcher/era/top3.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/pitcher/total/top5")
    public ResponseEntity<JsonNode> getMockPitcherTotal() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/pitcher/total/top5.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/pitcher/win/top3")
    public ResponseEntity<JsonNode> getMockPitcherWin() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/pitcher/win/top3.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/total/batter")
    public ResponseEntity<JsonNode> getMockTotalBatter() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/total/batter.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/total/pitcher")
    public ResponseEntity<JsonNode> getMockTotalPitcher() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/total/pitcher.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/batting")
    public ResponseEntity<JsonNode> getMockBatting() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/batting.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/periodteamrank")
    public ResponseEntity<JsonNode> getMockPeriodTeamRank() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/periodteamrank.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/pitching")
    public ResponseEntity<JsonNode> getMockPitching() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/pitching.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/teamvs")
    public ResponseEntity<JsonNode> getMockTeamVs() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/rank/teamvs.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/teamrankbyyear")
    public ResponseEntity<JsonNode> getMockTeamRankByYear() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/rank/teamrankbyyear.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }
}
