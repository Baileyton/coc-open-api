package com.example.coc.service;


import com.example.coc.controller.ContentController;
import com.example.coc.model.AbyssInfo;
import com.example.coc.model.GuardianInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@EnableCaching
public class ContentService {

    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Value("${api.key}")
    private String apiKey;

    public List<AbyssInfo> getAbyssInfo() {
        try {
            String url = "https://developer-lostark.game.onstove.com/gamecontents/challenge-abyss-dungeons";

            ResponseEntity<List<AbyssInfo>> response = WebClient.create()
                    .get()
                    .uri(url)
                    .header(HttpHeaders.AUTHORIZATION, "bearer " + apiKey)
                    .retrieve()
                    .toEntityList(AbyssInfo.class)
                    .block();

            if (response != null) {
                List<AbyssInfo> abyssInfo = response.getBody();
                logger.info("어비스 던전 조회 API 호출 성공: {}", response.getStatusCode());
                return abyssInfo;
            } else {
                logger.error("어비스 던전 조회 API 호출 실패");
                return null;
            }
        } catch (WebClientResponseException ex) {
            logger.error("어비스 던전 조회 API 호출 중 오류 발생: 응답 코드 - {}, 오류 메시지 - {}", ex.getRawStatusCode(), ex.getResponseBodyAsString(), ex);
            return null;
        } catch (Exception e) {
            logger.error("어비스 던전 조회 API 호출 중 오류 발생: {}", e.getMessage(), e);
            return null;
        }
    }

    public List<GuardianInfo> getGuardianInfo() {
        try {
            String url = "https://developer-lostark.game.onstove.com/gamecontents/challenge-guardian-dungeons";

            ResponseEntity<List<GuardianInfo>> response = WebClient.create()
                    .get()
                    .uri(url)
                    .header(HttpHeaders.AUTHORIZATION, "bearer " + apiKey)
                    .retrieve()
                    .toEntityList(GuardianInfo.class)
                    .block();

            if (response != null) {
                List<GuardianInfo> guardianInfo = response.getBody();
                logger.info("가디언 토벌 조회 API 호출 성공: {}", response.getStatusCode());
                return guardianInfo;
            } else {
                logger.error("가디언 토벌 조회 API 호출 실패");
                return null;
            }
        } catch (WebClientResponseException ex) {
            logger.error("가디언 토벌 조회 API 호출 중 오류 발생: 응답 코드 - {}, 오류 메시지 - {}", ex.getRawStatusCode(), ex.getResponseBodyAsString(), ex);
            return null;
        } catch (Exception e) {
            logger.error("가디언 토벌 조회 API 호출 중 오류 발생: {}", e.getMessage(), e);
            return null;
        }
    }
}
