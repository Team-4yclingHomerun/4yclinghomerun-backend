package com.example.demo.game.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

/**
 * packageName    : com.example.demo.game.controller
 * fileName       : GameController
 * author         : JAEIK
 * date           : 11/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/3/24       JAEIK       최초 생성
 */
@RestController
@Tag(name = "Json데이터:game")
@RequestMapping("/api/game")
public class GameController {

    @GetMapping("/boxscore0")
    public ResponseEntity<JsonNode> getMockBoxScore() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore0.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/boxscore1")
    public ResponseEntity<JsonNode> getMockBoxScore1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore1.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);

    }

    @GetMapping("/boxscore2")
    public ResponseEntity<JsonNode> getMockBoxScore2() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore2.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/boxscore3")
    public ResponseEntity<JsonNode> getMockBoxScore3() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore3.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/boxscore4")
    public ResponseEntity<JsonNode> getMockBoxScore4() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore4.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/boxscore5")
    public ResponseEntity<JsonNode> getMockBoxScore5() throws IOException {
        ObjectMapper objectMapper =  new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore5.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/ktwizteamrank")
    public ResponseEntity<JsonNode> getMockKtWizTeamRank() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/ktWizTeamRank/ktWizTeamRank.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthSchedule3")
    public ResponseEntity<JsonNode> getMockMonthSchedule3() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = new ClassPathResource("/data/game/monthSchedule/monthSchedule3.json");
        InputStream inputStream = resource.getInputStream();
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthSchedule4")
    public ResponseEntity<JsonNode> getMockMonthSchedule4() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule4.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthSchedule5")
    public ResponseEntity<JsonNode> getMockMonthSchedule5() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule5.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthSchedule6")
    public ResponseEntity<JsonNode> getMockMonthSchedule6() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule6.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthSchedule7")
    public ResponseEntity<JsonNode> getMockMonthSchedule7() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule7.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthSchedule8")
    public ResponseEntity<JsonNode> getMockMonthSchedule8() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule8.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthSchedule9")
    public ResponseEntity<JsonNode> getMockMonthSchedule9() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule9.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthSchedule10")
    public ResponseEntity<JsonNode> getMockMonthSchedule10() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule10.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/oddsWinning")
    public ResponseEntity<JsonNode> getMockOddsWinning() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/oddsWinning/oddsWinning.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/watchPoint0")
    public ResponseEntity<JsonNode> getMockWatchPoint0() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/ktAPI/watchPoint0.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/watchPoint1")
    public ResponseEntity<JsonNode> getMockWatchPoint1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/ktAPI/watchPoint1.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/watchPoint2")
    public ResponseEntity<JsonNode> getMockWatchPoint2() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/ktAPI/watchPoint2.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/watchPoint3")
    public ResponseEntity<JsonNode> getMockWatchPoint3() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/ktAPI/watchPoint3.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/watchPoint4")
    public ResponseEntity<JsonNode> getMockWatchPoint4() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/ktAPI/watchPoint4.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/watchPoint5")
    public ResponseEntity<JsonNode> getMockWatchPoint5() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/ktAPI/watchPoint5.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/naverWatchPoint0")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint0() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/naverAPI/naverWatchPoint0.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return  ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/naverWatchPoint1")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/naverAPI/naverWatchPoint1.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return  ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/naverWatchPoint2")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint2() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/naverAPI/naverWatchPoint2.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return  ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/naverWatchPoint3")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint3() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/naverAPI/naverWatchPoint3.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return  ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/naverWatchPoint4")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint4() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/naverAPI/naverWatchPoint4.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return  ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/naverWatchPoint5")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint5() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchPoint/naverAPI/naverWatchPoint5.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return  ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/weekSchedule")
    public ResponseEntity<JsonNode> getMockWeekSchedule() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/weekSchedule/weekSchedule.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }
}
