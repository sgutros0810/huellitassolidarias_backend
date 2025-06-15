package com.huellitassolidarias.huellitassolidarias_backend.controller;

import com.huellitassolidarias.huellitassolidarias_backend.enums.City;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    @GetMapping
    public ResponseEntity<City[]> getAllCities() {
        return ResponseEntity.ok(City.values());
    }
}