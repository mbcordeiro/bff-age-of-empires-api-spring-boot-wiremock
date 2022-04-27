package com.matheuscordeiro.bffageofempiresapi.services;

import com.matheuscordeiro.bffageofempiresapi.clients.interfaces.AgeOfEmpiresClient;
import com.matheuscordeiro.bffageofempiresapi.dtos.response.CivilizationResponse;
import com.matheuscordeiro.bffageofempiresapi.exception.NotFoundException;
import com.matheuscordeiro.bffageofempiresapi.services.interfaces.CivilizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CivilizationServiceImpl implements CivilizationService {
    private final AgeOfEmpiresClient client;

    @Override
    public List<CivilizationResponse> getCivilizations() {
        return client.findCivilizations().getCivilizations().stream().map(civilization ->
                CivilizationResponse
                        .builder()
                        .id(civilization.getId())
                        .name(civilization.getName())
                        .armyType(civilization.getArmyType())
                        .teamBonus(civilization.getTeamBonus())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public CivilizationResponse getCivilizationById(Long id) {
        try {
            final var civilization = client.findCivilizationById(id);
            return CivilizationResponse
                    .builder()
                    .id(civilization.getId())
                    .name(civilization.getName())
                    .armyType(civilization.getArmyType())
                    .teamBonus(civilization.getTeamBonus())
                    .build();
        } catch (Exception e) {
            throw new NotFoundException("Civilization not found!");
        }
    }
}
