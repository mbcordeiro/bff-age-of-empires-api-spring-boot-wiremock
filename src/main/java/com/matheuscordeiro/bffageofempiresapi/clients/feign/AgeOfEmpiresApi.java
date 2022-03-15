package com.matheuscordeiro.bffageofempiresapi.clients.feign;

import com.matheuscordeiro.bffageofempiresapi.clients.response.CivilizationClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(contextId = "AgeOfEmpiresApi", name = "${ageOfEmpiresPublicAPIV1.name:ageOfEmpiresPublicAPIV1}", url = "${ageOfEmpiresPublicAPIV1.url:https://age-of-empires-2-api.herokuapp.com/api/v1}")
@Validated
public interface AgeOfEmpiresApi {
    @GetMapping("/civilizations")
    ResponseEntity<List<CivilizationClientResponse>> listCivilizations();
}
