package com.matheuscordeiro.bffageofempiresapi.clients;

import com.matheuscordeiro.bffageofempiresapi.clients.feign.AgeOfEmpiresApi;
import com.matheuscordeiro.bffageofempiresapi.clients.interfaces.AgeOfEmpiresClient;
import com.matheuscordeiro.bffageofempiresapi.clients.response.CivilizationClientResponse;
import com.matheuscordeiro.bffageofempiresapi.clients.response.CivilizationClientResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgeOfEmpiresClientImpl implements AgeOfEmpiresClient {
    private final AgeOfEmpiresApi ageOfEmpiresApi;

    @Override
    public CivilizationClientResponseList findCivilizations() {
        return ageOfEmpiresApi.listCivilizations().getBody();
    }

    @Override
    public CivilizationClientResponse findCivilizationById(Long id) {
        return ageOfEmpiresApi.getCivilizationById(id);
    }
}
