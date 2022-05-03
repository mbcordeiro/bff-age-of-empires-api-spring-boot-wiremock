package com.matheuscordeiro.bffageofempiresapi.clients.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CivilizationClientResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("expansion")
    private String expansion;

    @JsonProperty("army_type")
    private String armyType;

    @JsonProperty("unique_unit")
    private List<String> uniqueUnit;

    @JsonProperty("unique_tech")
    private List<String> uniqueTech;

    @JsonProperty("team_bonus")
    private String teamBonus;

    @JsonProperty("civilization_bonus")
    private List<String> civilizationBonus;
}
