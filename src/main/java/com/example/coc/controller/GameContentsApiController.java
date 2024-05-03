package com.example.coc.controller;

import com.example.coc.model.CalendarInfo;
import com.example.coc.model.CharacterInfo;
import com.example.coc.model.EventInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class GameContentsApiController {

    private static final Logger logger = LoggerFactory.getLogger(GameContentsApiController.class);

    @Value("${api.key}")
    private String apiKey;

    @ApiOperation(value = "도전 어비스 던전 조회 API", notes = "이번주 도전 어비스 던전을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = CharacterInfo.class),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 403, message = "접근 거부"),
            @ApiResponse(code = 404, message = "데이터를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "내부 서버 오류")
    })
    @GetMapping("/abyss")
    public String abyssInfo(Model model) {
        try {
            String url = "https://developer-lostark.game.onstove.com/gamecontents/challenge-abyss-dungeons";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authorization", "bearer " + apiKey);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            System.out.println(response.getBody());

            ObjectMapper objectMapper = new ObjectMapper();
//            List<EventInfo> eventInfoList = objectMapper.readValue(response.getBody(), new TypeReference<List<EventInfo>>() {});
//            model.addAttribute("eventInfoList", eventInfoList);
//            System.out.println(eventInfoList);
        } catch (Exception e) {
            model.addAttribute("response", "Failed to fetch character information.");
        }

        return "challenge-abyss-dungeons";
    }

    @ApiOperation(value = "도전 가디언 토벌 조회 API", notes = "이번주 도전 가디언 토벌을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = CharacterInfo.class),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 403, message = "접근 거부"),
            @ApiResponse(code = 404, message = "데이터를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "내부 서버 오류")
    })
    @GetMapping("/guardian")
    public String guardianInfo(Model model) {
        try {
            String url = "https://developer-lostark.game.onstove.com/gamecontents/challenge-guardian-dungeons";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authorization", "bearer " + apiKey);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            System.out.println(response.getBody());

            ObjectMapper objectMapper = new ObjectMapper();
            List<EventInfo> eventInfoList = objectMapper.readValue(response.getBody(), new TypeReference<List<EventInfo>>() {});
            model.addAttribute("eventInfoList", eventInfoList);
            System.out.println(eventInfoList);
        } catch (Exception e) {
            model.addAttribute("response", "Failed to fetch character information.");
        }

        return "challenge-guardian-dungeons";
    }

    @ApiOperation(value = "캘린더 조회 API", notes = "캘린더를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = CharacterInfo.class),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 403, message = "접근 거부"),
            @ApiResponse(code = 404, message = "데이터를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "내부 서버 오류")
    })
    @GetMapping("/calendars")
    public ResponseEntity<List<CalendarInfo>> calendarInfo() {
        try {
            String url = "https://developer-lostark.game.onstove.com/gamecontents/calendar";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authorization", "bearer " + apiKey);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();

            List<CalendarInfo> calendarInfosList = objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });

            logger.info("캘린더 조회 API 호출 : {}", response.getStatusCode());
            return ResponseEntity.ok().body(calendarInfosList);
        } catch (Exception e) {
            logger.error("캘린더 조회 API 호출 중 오류 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
