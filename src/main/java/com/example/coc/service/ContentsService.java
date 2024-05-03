package com.example.coc.service;

import com.example.coc.controller.ContentsController;
import com.example.coc.model.CalendarInfo;
import com.example.coc.model.EventInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@EnableCaching
public class ContentsService {
    private static final Logger logger = LoggerFactory.getLogger(ContentsController.class);

    @Value("${api.key}")
    private String apiKey;

    @Cacheable(value = "calendarInfo")
    public List<CalendarInfo> getCalendarInfo() {
        try {
            String url = "https://developer-lostark.game.onstove.com/gamecontents/calendar";

            ResponseEntity<List<CalendarInfo>> response = WebClient.create()
                    .get()
                    .uri(url)
                    .header(HttpHeaders.AUTHORIZATION, "bearer " + apiKey)
                    .retrieve()
                    .toEntityList(CalendarInfo.class)
                    .block();

            if (response != null) {
                List<CalendarInfo> calendarInfo = response.getBody();
                logger.info("캘린더 조회 API 호출 성공: {}", response.getStatusCode());
                return calendarInfo;
            } else {
                logger.error("캘린더 조회 API 호출 실패");
                return null;
            }
        } catch (WebClientResponseException ex) {
            logger.error("캘린더 조회 API 호출 중 오류 발생: 응답 코드 - {}, 오류 메시지 - {}", ex.getRawStatusCode(), ex.getResponseBodyAsString(), ex);
            return null;
        } catch (Exception e) {
            logger.error("캘린더 조회 API 호출 중 오류 발생: {}", e.getMessage(), e);
            return null;
        }
    }
}
