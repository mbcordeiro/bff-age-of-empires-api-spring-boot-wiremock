package com.matheuscordeiro.bffageofempiresapi.clients.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
public class CivilizationClientResponseList {
    @JsonProperty("civilizations")
    private List<CivilizationClientResponse> civilizations;
}
