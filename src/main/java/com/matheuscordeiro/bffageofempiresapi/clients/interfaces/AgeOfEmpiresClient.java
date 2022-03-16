package com.matheuscordeiro.bffageofempiresapi.clients.interfaces;

import com.matheuscordeiro.bffageofempiresapi.clients.response.CivilizationClientResponseList;

public interface AgeOfEmpiresClient {
    CivilizationClientResponseList findCivilizations();
}
