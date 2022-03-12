package com.matheuscordeiro.bffageofempiresapi.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CivilizationResponse {
    private Long id;
    private String name;
    private String armyType;
    private String teamBonus;
}
