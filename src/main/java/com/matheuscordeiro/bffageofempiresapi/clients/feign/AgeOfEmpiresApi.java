package com.matheuscordeiro.bffageofempiresapi.clients.feign;

import com.matheuscordeiro.bffageofempiresapi.clients.response.CivilizationClientResponse;
import com.matheuscordeiro.bffageofempiresapi.clients.response.CivilizationClientResponseList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "AgeOfEmpiresApi", name = "${ageOfEmpiresPublicAPIV1.name:ageOfEmpiresPublicAPIV1}", url = "${ageOfEmpiresPublicAPIV1.url:https://age-of-empires-2-api.herokuapp.com/api/v1}")
@Validated
public interface AgeOfEmpiresApi {
    @GetMapping(value = "/civilizations", produces = "*/*")
    ResponseEntity<CivilizationClientResponseList> listCivilizations();

    @GetMapping(value = "/civilization/{id}", produces = "*/*")
    CivilizationClientResponse getCivilizationById(@PathVariable Long id);
}
