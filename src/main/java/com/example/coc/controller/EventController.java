package com.example.coc.controller;

import com.example.coc.model.EventInfo;
import com.example.coc.service.EventService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @ApiOperation(value = "이벤트 조회 API", notes = "진행 중인 이벤트를 조회 합니다.")
    @GetMapping
    public ResponseEntity<List<EventInfo>> getEvent() {
        List<EventInfo> eventInfo = eventService.getEventInfo();
        if (eventInfo != null && !eventInfo.isEmpty()) {
            return ResponseEntity.ok(eventInfo);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
