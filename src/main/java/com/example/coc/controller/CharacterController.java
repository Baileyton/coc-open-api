package com.example.coc.controller;

import com.example.coc.model.CharacterInfo;
import com.example.coc.service.CharacterService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping(value = "/api")
    public String charSearch(){
        return "index";
    }

    @ApiOperation(value = "캐릭터 프로필 조회 API", notes = "입력한 캐릭터명을 포함한 계정의 모든 캐릭터 프로필을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공", response = CharacterInfo.class)
    })
    @GetMapping("/char/{charName}")
    public String charInfo(@PathVariable("charName") String charName, Model model) {
        List<CharacterInfo> characterInfoList = characterService.getCharacterInfo(charName);
        model.addAttribute("characterInfoList", characterInfoList);
        return "charInfo";
    }
}
