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
    private Long id;
    private String name;
    private String expansion;
    private String armyType;
    private List<String> uniqueUnit;
    private List<String> uniqueTech;
    private String teamBonus;
    private List<String> civilizationBonus;
}
