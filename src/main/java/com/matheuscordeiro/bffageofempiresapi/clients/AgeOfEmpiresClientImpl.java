package com.matheuscordeiro.bffageofempiresapi.clients;

import com.matheuscordeiro.bffageofempiresapi.clients.feign.AgeOfEmpiresApi;
import com.matheuscordeiro.bffageofempiresapi.clients.interfaces.AgeOfEmpiresClient;
import com.matheuscordeiro.bffageofempiresapi.clients.response.CivilizationClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AgeOfEmpiresClientImpl implements AgeOfEmpiresClient {
    private final AgeOfEmpiresApi ageOfEmpiresApi;

    @Override
    public List<CivilizationClientResponse> findCivilizations() {
        return ageOfEmpiresApi.listCivilizations().getBody();
    }
}
