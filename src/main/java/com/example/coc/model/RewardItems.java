package com.example.coc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RewardItems {
    @JsonProperty("ExpeditionItemLevel")
    private int expeditionItemLevel;
    @JsonProperty("Items")
    private List<RewardItem> items;
}
