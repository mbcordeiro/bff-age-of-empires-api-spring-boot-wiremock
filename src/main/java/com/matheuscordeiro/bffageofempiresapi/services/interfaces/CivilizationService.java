package com.matheuscordeiro.bffageofempiresapi.services.interfaces;

import com.matheuscordeiro.bffageofempiresapi.dtos.response.CivilizationResponse;

import java.util.List;

public interface CivilizationService {
    List<CivilizationResponse> getCivilizations();
}
