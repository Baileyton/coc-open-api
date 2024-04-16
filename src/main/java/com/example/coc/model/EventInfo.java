package com.example.coc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EventInfo {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Thumbnail")
    private String thumbnail;

    @JsonProperty("Link")
    private String link;

    @JsonProperty("StartDate")
    private String startDate;

    @JsonProperty("EndDate")
    private String endDate;

    @JsonProperty("RewardDate")
    private String rewardDate;
}
