package com.matheuscordeiro.bffageofempiresapi.services;

import com.matheuscordeiro.bffageofempiresapi.clients.interfaces.AgeOfEmpiresClient;
import com.matheuscordeiro.bffageofempiresapi.dtos.response.CivilizationResponse;
import com.matheuscordeiro.bffageofempiresapi.services.interfaces.CivilizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CivilizationServiceImpl implements CivilizationService {
    private final AgeOfEmpiresClient client;

    @Override
    public CivilizationResponse getCivilizations() {
        client.findCivilizations();
        return CivilizationResponse.builder().build();
    }
}
