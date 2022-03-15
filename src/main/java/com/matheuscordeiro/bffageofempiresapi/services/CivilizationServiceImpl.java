package com.matheuscordeiro.bffageofempiresapi.services;

import com.matheuscordeiro.bffageofempiresapi.clients.interfaces.AgeOfEmpiresClient;
import com.matheuscordeiro.bffageofempiresapi.dtos.response.CivilizationResponse;
import com.matheuscordeiro.bffageofempiresapi.services.interfaces.CivilizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CivilizationServiceImpl implements CivilizationService {
    private final AgeOfEmpiresClient client;

    @Override
    public List<CivilizationResponse> getCivilizations() {
        try {
            final var listCivilizations = client.findCivilizations();
            if (listCivilizations.isEmpty()) return Collections.EMPTY_LIST;
            return listCivilizations.stream().map(civilization ->
                    CivilizationResponse
                            .builder()
                            .id(civilization.getId())
                            .name(civilization.getName())
                            .armyType(civilization.getArmyType())
                            .teamBonus(civilization.getTeamBonus())
                            .build()
            ).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
