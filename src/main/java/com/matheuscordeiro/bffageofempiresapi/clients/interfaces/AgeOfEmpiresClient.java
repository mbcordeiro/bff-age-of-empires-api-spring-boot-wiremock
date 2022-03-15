package com.matheuscordeiro.bffageofempiresapi.clients.interfaces;

import com.matheuscordeiro.bffageofempiresapi.clients.response.CivilizationClientResponse;

import java.util.List;

public interface AgeOfEmpiresClient {
    List<CivilizationClientResponse> findCivilizations();
}
