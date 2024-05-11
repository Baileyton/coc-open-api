package com.example.coc.controller;

import com.example.coc.model.AbyssInfo;
import com.example.coc.model.CalendarInfo;
import com.example.coc.model.GuardianInfo;
import com.example.coc.service.ContentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @ApiOperation(value = "도전 어비스 던전 조회 API", notes = "이번주 도전 어비스 던전을 조회합니다.")
    @GetMapping(value = "/abyss")
    public ResponseEntity<List<AbyssInfo>> getAbyss() {
        List<AbyssInfo> abyssInfo = contentService.getAbyssInfo();
        if (abyssInfo != null && !abyssInfo.isEmpty()) {
            return ResponseEntity.ok(abyssInfo);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @ApiOperation(value = "도전 가디언 토벌 조회 API", notes = "이번주 도전 가디언 토벌을 조회합니다.")
    @GetMapping("/guardian")
    public ResponseEntity<List<GuardianInfo>> getGuardian() {
        List<GuardianInfo> guardianInfo = contentService.getGuardianInfo();
        if (guardianInfo != null && !guardianInfo.isEmpty()) {
            return ResponseEntity.ok(guardianInfo);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @ApiOperation(value = "캘린더 조회 API", notes = "오늘 컨텐츠를 조회합니다.")
    @GetMapping("/calendar")
    public ResponseEntity<List<CalendarInfo>> getCalendar() {
        List<CalendarInfo> calendarInfo = contentService.getCalendarInfo();
        if (calendarInfo != null && !calendarInfo.isEmpty()) {
            return ResponseEntity.ok(calendarInfo);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
