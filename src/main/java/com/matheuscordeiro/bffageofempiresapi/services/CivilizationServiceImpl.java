package com.matheuscordeiro.bffageofempiresapi.services;

import com.matheuscordeiro.bffageofempiresapi.clients.interfaces.AgeOfEmpiresClient;
import com.matheuscordeiro.bffageofempiresapi.dtos.response.CivilizationResponse;
import com.matheuscordeiro.bffageofempiresapi.exception.NotFoundException;
import com.matheuscordeiro.bffageofempiresapi.mapper.CivilizationMapper;
import com.matheuscordeiro.bffageofempiresapi.services.interfaces.CivilizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CivilizationServiceImpl implements CivilizationService {
    private final AgeOfEmpiresClient client;

    private final CivilizationMapper civilizationMapper;

    @Cacheable("civilizations")
    @Override
    public List<CivilizationResponse> getCivilizations() {
        try {
            return client.findCivilizations().getCivilizations().stream()
                    .map(civilizationMapper::toCivilizationResponse).collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundException("Civilization not found!");
        }
    }

    @Cacheable("civilizations")
    @Override
    public CivilizationResponse getCivilizationById(Long id) {
        try {
            final var civilization = client.findCivilizationById(id);
            return civilizationMapper.toCivilizationResponse(civilization);
        } catch (Exception e) {
            throw new NotFoundException("Civilization not found!");
        }
    }
}
