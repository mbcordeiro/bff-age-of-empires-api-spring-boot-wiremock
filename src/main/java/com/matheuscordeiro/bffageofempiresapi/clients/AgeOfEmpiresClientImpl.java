package com.matheuscordeiro.bffageofempiresapi.clients;

import com.matheuscordeiro.bffageofempiresapi.clients.feign.AgeOfEmpiresApi;
import com.matheuscordeiro.bffageofempiresapi.clients.interfaces.AgeOfEmpiresClient;
import com.matheuscordeiro.bffageofempiresapi.clients.response.CivilizationClientResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AgeOfEmpiresClientImpl implements AgeOfEmpiresClient {
    private final AgeOfEmpiresApi ageOfEmpiresApi;

    @Override
    public List<CivilizationClientResponse> findCivilizations() {
        return ageOfEmpiresApi.listCivilizations().getBody();
    }
}
