package com.example.geoapi.model;

public record City(
        Long id,
        String name,
        Long countryId,
        Long population,
        String region,
        String timezone
) {
}
