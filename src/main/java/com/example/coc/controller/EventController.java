package com.example.coc.controller;

import com.example.coc.model.EventInfo;
import com.example.coc.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @ApiOperation(value = "이벤트 조회 API", notes = "진행 중인 이벤트를 조회 합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = EventInfo.class)
    })
    @GetMapping("/event")
    public String charInfo(Model model) {
        List<EventInfo> eventInfoList = eventService.getEventInfo();
        model.addAttribute("eventInfoList", eventInfoList);
        return "eventInfo";
    }
}
