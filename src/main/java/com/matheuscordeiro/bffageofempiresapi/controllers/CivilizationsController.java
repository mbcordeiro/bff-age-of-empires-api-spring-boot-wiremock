package com.matheuscordeiro.bffageofempiresapi.controllers;

import com.matheuscordeiro.bffageofempiresapi.dtos.response.CivilizationResponse;
import com.matheuscordeiro.bffageofempiresapi.services.interfaces.CivilizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/civilization")
@RequiredArgsConstructor
public class CivilizationsController {
    private final CivilizationService civilizationService;

    @GetMapping("/civilizations")
    public ResponseEntity<List<CivilizationResponse>> getCivilizations() {
        return ResponseEntity.ok().body(civilizationService.getCivilizations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CivilizationResponse> getCivilizationById(@PathVariable final Long id) {
        return ResponseEntity.ok().body(civilizationService.getCivilizationById(id));
    }
}
