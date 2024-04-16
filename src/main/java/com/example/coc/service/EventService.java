package com.example.coc.service;

import com.example.coc.controller.EventController;
import com.example.coc.model.EventInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Value("${api.key}")
    private String apiKey;

    public List<EventInfo> getEventInfo() {
        try {
            String url = "https://developer-lostark.game.onstove.com/news/events";

            ResponseEntity<List<EventInfo>> response = WebClient.create()
                    .get()
                    .uri(url)
                    .header(HttpHeaders.AUTHORIZATION, "bearer " + apiKey)
                    .retrieve()
                    .toEntityList(EventInfo.class)
                    .block();

            if (response != null) {
                List<EventInfo> eventInfoList = response.getBody();
                logger.info("이벤트 조회 API 호출 성공: {}", response.getStatusCode());
                return eventInfoList;
            } else {
                logger.error("이벤트 조회 API 호출 실패: {}", response.getStatusCode());
                return null;
            }
        } catch (WebClientResponseException ex) {
            logger.error("이벤트 조회 API 호출 중 오류 발생: 응답 코드 - {}, 오류 메시지 - {}", ex.getRawStatusCode(), ex.getResponseBodyAsString(), ex);
            return null;
        } catch (Exception e) {
            logger.error("이벤트 조회 API 호출 중 오류 발생: {}", e.getMessage(), e);
            return null;
        }
    }
}
