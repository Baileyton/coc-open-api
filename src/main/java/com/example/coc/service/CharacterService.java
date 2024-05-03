package com.example.coc.service;

import com.example.coc.controller.CharacterController;
import com.example.coc.model.CharacterInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@CacheConfig(cacheNames = "characterInfo")
public class CharacterService {

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);

    @Value("${api.key}")
    private String apiKey;

    @Cacheable(key = "#charName")
    public List<CharacterInfo> getCharacterInfo(String charName) {
        try {
            String url = "https://developer-lostark.game.onstove.com/characters/" + charName + "/siblings";

            List<CharacterInfo> characterInfoList = WebClient.create()
                    .get()
                    .uri(url)
                    .header(HttpHeaders.AUTHORIZATION, "bearer " + apiKey)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<CharacterInfo>>() {})
                    .block();

            logger.info("캐릭터 프로필 조회 API 호출 성공: {}", charName);
            return characterInfoList;
        } catch (Exception e) {
            logger.error("캐릭터 프로필 조회 API 호출 중 오류 발생: {}", e.getMessage());
            return null;
        }
    }
}
