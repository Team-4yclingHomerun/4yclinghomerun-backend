package com.example.demo.game.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/boxscore/gameDate-20241011/gmkey-33331011KTLG0")
    public ResponseEntity<JsonNode> getMockBoxScore() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore0.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/boxscore/gameDate-20241009/gmkey-33331009LGKT0")
    public ResponseEntity<JsonNode> getMockBoxScore1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore1.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);

    }

    @GetMapping("/boxscore/gameDate-20241008/gmkey-33331008LGKT0")
    public ResponseEntity<JsonNode> getMockBoxScore2() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore2.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/boxscore/gameDate-20241006/gmkey-33331006KTLG0")
    public ResponseEntity<JsonNode> getMockBoxScore3() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore3.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/boxscore/gameDate-20241005/gmkey-33331005KTLG0")
    public ResponseEntity<JsonNode> getMockBoxScore4() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore4.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/boxscore/gameDate-20240928/gmkey-20240928WOKT0")
    public ResponseEntity<JsonNode> getMockBoxScore5() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/boxscore/boxscore5.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/ktwizteamrank")
    public ResponseEntity<JsonNode> getMockKtWizTeamRank() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/ktTeamRank/ktWizTeamRank.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthschedule/yearMonth-202403")
    public ResponseEntity<JsonNode> getMockMonthSchedule3() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = new ClassPathResource("/data/game/monthSchedule/monthSchedule3.json");
        InputStream inputStream = resource.getInputStream();
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthschedule/yearMonth-202404")
    public ResponseEntity<JsonNode> getMockMonthSchedule4() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule4.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthschedule/yearMonth-202405")
    public ResponseEntity<JsonNode> getMockMonthSchedule5() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule5.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthschedule/yearMonth-202406")
    public ResponseEntity<JsonNode> getMockMonthSchedule6() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule6.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthschedule/yearMonth-202407")
    public ResponseEntity<JsonNode> getMockMonthSchedule7() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule7.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthschedule/yearMonth-202408")
    public ResponseEntity<JsonNode> getMockMonthSchedule8() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule8.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthschedule/yearMonth-202409")
    public ResponseEntity<JsonNode> getMockMonthSchedule9() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/monthSchedule/monthSchedule9.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/monthschedule/yearMonth-202410")
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

    @GetMapping("/watchpoint/gameDate-20241011/gmkey-33331011KTLG0")
    public ResponseEntity<JsonNode> getMockWatchPoint0() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchpoint/ktAPI/watchPoint0.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/watchpoint/gameDate-20241009/gmkey-33331009LGKT0")
    public ResponseEntity<JsonNode> getMockWatchPoint1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchpoint/ktAPI/watchPoint1.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/watchpoint/gameDate-20241008/gmkey-33331008LGKT0")
    public ResponseEntity<JsonNode> getMockWatchPoint2() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchpoint/ktAPI/watchPoint2.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/watchpoint/gameDate-20241006/gmkey-33331006KTLG0")
    public ResponseEntity<JsonNode> getMockWatchPoint3() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchpoint/ktAPI/watchPoint3.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/watchpoint/gameDate-20241005/gmkey-33331005KTLG0")
    public ResponseEntity<JsonNode> getMockWatchPoint4() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchpoint/ktAPI/watchPoint4.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/watchpoint/gameDate-20240928/gmkey-20240928WOKT0")
    public ResponseEntity<JsonNode> getMockWatchPoint5() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/watchpoint/ktAPI/watchPoint5.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/navergameinfo/gmkey-33331011KTLG0")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint0() throws IOException {
        return getMockNaverWatchPoint("/data/game/watchpoint/naverAPI/naverWatchPoint0.json");
    }

    @GetMapping("/navergameinfo/gmkey-33331009LGKT0")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint1() throws IOException {
        return  getMockNaverWatchPoint("/data/game/watchpoint/naverAPI/naverWatchPoint1.json" );
    }

    @GetMapping("/navergameinfo/gmkey-33331008LGKT0")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint2() throws IOException {
        return  getMockNaverWatchPoint("/data/game/watchpoint/naverAPI/naverWatchPoint2.json" );
    }

    @GetMapping("/navergameinfo/gmkey-33331006KTLG0")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint3() throws IOException {
        return  getMockNaverWatchPoint("/data/game/watchpoint/naverAPI/naverWatchPoint3.json" );
    }

    @GetMapping("/navergameinfo/gmkey-33331005KTLG0")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint4() throws IOException {
        return  getMockNaverWatchPoint("/data/game/watchpoint/naverAPI/naverWatchPoint4.json" );
    }

    @GetMapping("/navergameinfo/gmkey-20240928WOKT0")
    public ResponseEntity<JsonNode> getMockNaverWatchPoint5() throws IOException {
        return  getMockNaverWatchPoint("/data/game/watchpoint/naverAPI/naverWatchPoint5.json" );
    }

    @GetMapping("/weekSchedule")
    public ResponseEntity<JsonNode> getMockWeekSchedule() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/game/weekSchedule/weekSchedule.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    private ResponseEntity<JsonNode> getMockNaverWatchPoint(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream(jsonFilePath);
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);


    }
}
