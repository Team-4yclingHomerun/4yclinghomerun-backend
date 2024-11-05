package com.example.demo.news.controller;

import com.example.demo.news.dto.NewsListSearchWordRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

/**
 * packageName    : com.example.demo.news.controller
 * fileName       : NewsController
 * author         : JAEIK
 * date           : 11/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 11/4/24       JAEIK       최초 생성
 */
@RestController
@Slf4j
@Tag(name = "Json데이터:news")
@RequestMapping("/api/article")
public class NewsController {

    @GetMapping("/navernewsdetail/oid/022-0003979014")
    public ResponseEntity<JsonNode> gerMockNaverNewsDetail022() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_navernewslist_detail/navernewsdetail-oid=022&aid=0003979014.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/navernewsdetail/oid/109-0005180565")
    public ResponseEntity<JsonNode> gerMockNaverNewsDetail0() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_navernewslist_detail/navernewsdetail-oid=109&aid=0005180565.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/navernewsdetail/oid/109-0005182204")
    public ResponseEntity<JsonNode> gerMockNaverNewsDetail1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_navernewslist_detail/navernewsdetail-oid=109&aid=0005182204.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/navernewsdetail/oid/109-0005182942")
    public ResponseEntity<JsonNode> gerMockNaverNewsDetail2() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_navernewslist_detail/navernewsdetail-oid=109&aid=0005182942.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/navernewsdetail/oid/109-0005183900")
    public ResponseEntity<JsonNode> gerMockNaverNewsDetail3() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_navernewslist_detail/navernewsdetail-oid=109&aid=0005183900.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/navernewsdetail/oid/241-0003390581")
    public ResponseEntity<JsonNode> gerMockNaverNewsDetail4() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_navernewslist_detail/navernewsdetail-oid=241&aid=0003390581.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/navernewsdetail/oid/311-0001787145")
    public ResponseEntity<JsonNode> gerMockNaverNewsDetail5() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_navernewslist_detail/navernewsdetail-oid=311&aid=0001787145.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/navernewsdetail/oid/396-0000692824")
    public ResponseEntity<JsonNode> gerMockNaverNewsDetail6() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_navernewslist_detail/navernewsdetail-oid=396&aid=0000692824.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/navernewslist")
    public ResponseEntity<JsonNode> getMockNaverNewsList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_navernewslist_detail/navernewslist.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/newslist")
    public ResponseEntity<JsonNode> getMockNewsListSearchWord(@RequestParam String searchWord) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_newslistlist_detail/newslist.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);

        // ArrayNode 결과를 저장하기위해 객체생성
        ArrayNode filterResults = objectMapper.createArrayNode();

        // 특정 노드에 접근하는 이름 지정
        JsonNode listNode = jsonNode.path("data").path("list");

        for (JsonNode item: listNode) {
            String title =item.has("artcTitle") ? item.get("artcTitle").asText(): "";
            String contents =item.has("artcContents") ? item.get("artcContents").asText(): "";
            log.info("title{}:",title);

            if (title.contains(searchWord) || contents.contains(searchWord)) {
                filterResults.add(item);
            }
        }
        return ResponseEntity.ok(filterResults);
    }

//    @GetMapping("/newslist")
//    public ResponseEntity<JsonNode> getMockNewsList() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_newslistlist_detail/newslist.json");
//        JsonNode jsonNode = objectMapper.readTree(inputStream);
//        return ResponseEntity.ok(jsonNode);
//    }

    @GetMapping("/newsdetail/artcSeq-187627")
    public ResponseEntity<JsonNode> getMockNewsDetailArtcSeq() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_newslistlist_detail/newsdetail-artcSeq=187627.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/wizpresslist")
    public ResponseEntity<JsonNode> getMockWizPressList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_wizpresslist_detail/wizpresslist.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/wizpressdetail/artcSeq-177032")
    public ResponseEntity<JsonNode> getMockWizPressDetailArtcSeq() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/news/news_wizpresslist_detail/wizpresslist-artcSeq=177032.json");
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        return ResponseEntity.ok(jsonNode);
    }
}
