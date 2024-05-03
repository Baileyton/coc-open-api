package com.example.coc.controller;

import com.example.coc.model.CalendarInfo;
import com.example.coc.model.EventInfo;
import com.example.coc.service.ContentsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContentsController {

    private final ContentsService contentsService;

    @Autowired
    public ContentsController(ContentsService contentsService) {
        this.contentsService = contentsService;
    }

    @ApiOperation(value = "캘린더 조회 API", notes = "오늘 컨텐츠를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = CalendarInfo.class)
    })
    @GetMapping("/calendar")
    public String calendarInfo(Model model) {
        List<CalendarInfo> calendarInfo = contentsService.getCalendarInfo();
        model.addAttribute("calendarInfo", calendarInfo);
        return "calendarInfo";
    }
}
