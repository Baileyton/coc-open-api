package com.example.coc.controller;

import com.example.coc.model.CharacterInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Controller
public class CharactersApiController {

    @Value("${api.key}")
    private String apiKey;

    @ApiOperation(value = "캐릭터 프로필 조회 API", notes = "입력한 캐릭터명을 포함한 계정의 모든 캐릭터 프로필을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = CharacterInfo.class),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 403, message = "접근 거부"),
            @ApiResponse(code = 404, message = "데이터를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "내부 서버 오류")
    })
    @GetMapping("/char/{charName}")
    public ResponseEntity<List<CharacterInfo>> charInfo(@PathVariable("charName") String charName) {
        try {
            String url = "https://developer-lostark.game.onstove.com/characters/" + charName + "/siblings";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authorization", "bearer " + apiKey);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();

            List<CharacterInfo> characterInfoList = objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<CharacterInfo>>() {});

            return ResponseEntity.ok().body(characterInfoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
