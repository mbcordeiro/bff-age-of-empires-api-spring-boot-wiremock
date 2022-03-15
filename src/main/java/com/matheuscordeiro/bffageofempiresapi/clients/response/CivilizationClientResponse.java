package com.matheuscordeiro.bffageofempiresapi.clients.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
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
