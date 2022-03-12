package com.matheuscordeiro.bffageofempiresapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/civilization")
public class CivilizationsController {
    @GetMapping
    public ResponseEntity<?> getCivilizations() {
        return ResponseEntity.ok().body("Hello civilization");
    }
}
