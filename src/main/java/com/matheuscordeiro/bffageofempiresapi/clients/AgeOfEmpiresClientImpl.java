package com.matheuscordeiro.bffageofempiresapi.clients;

import com.matheuscordeiro.bffageofempiresapi.clients.interfaces.AgeOfEmpiresClient;
import com.matheuscordeiro.bffageofempiresapi.clients.response.CivilizationClientResponse;

public class AgeOfEmpiresClientImpl implements AgeOfEmpiresClient {
    private final MarvelApi marvelApi;
    @Override
    public CivilizationClientResponse findCivilizations() {
        return null;
    }
}