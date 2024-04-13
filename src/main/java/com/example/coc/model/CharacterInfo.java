package com.example.coc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharacterInfo {
    @JsonProperty("ServerName")
    private String serverName;

    @JsonProperty("CharacterName")
    private String characterName;

    @JsonProperty("CharacterLevel")
    private Integer characterLevel;

    @JsonProperty("CharacterClassName")
    private String characterClassName;

    @JsonProperty("ItemAvgLevel")
    private String itemAvgLevel;

    @JsonProperty("ItemMaxLevel")
    private String itemMaxLevel;
}
