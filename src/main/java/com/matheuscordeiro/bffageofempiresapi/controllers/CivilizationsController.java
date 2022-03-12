package com.matheuscordeiro.bffageofempiresapi.controllers;

import com.matheuscordeiro.bffageofempiresapi.dtos.response.CivilizationResponse;
import com.matheuscordeiro.bffageofempiresapi.services.interfaces.CivilizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/civilization")
@RequiredArgsConstructor
public class CivilizationsController {
    private final CivilizationService civilizationService;

    @GetMapping
    public ResponseEntity<CivilizationResponse> getCivilizations() {
        return ResponseEntity.ok().body(civilizationService.getCivilizations());
    }
}
