package com.example.coc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AbyssInfo {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("MinCharacterLevel")
    private int minCharacterLevel;
    @JsonProperty("MinItemLevel")
    private int minItemLevel;
    @JsonProperty("AreaName")
    private String areaName;
    @JsonProperty("StartTime")
    private String startTime;
    @JsonProperty("EndTime")
    private String endTime;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("RewardItems")
    private List<RewardItem> rewardItems;
}
