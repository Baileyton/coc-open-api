package com.example.coc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GuardianInfo {
    @JsonProperty("Raids")
    private List<Raids> raids;
    @JsonProperty("RewardItems")
    private List<RewardItems> rewardItems;
}
