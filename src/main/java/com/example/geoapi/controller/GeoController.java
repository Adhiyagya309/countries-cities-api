package com.example.geoapi.controller;

import com.example.geoapi.model.City;
import com.example.geoapi.model.Country;
import com.example.geoapi.model.PagedResponse;
import com.example.geoapi.service.GeoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class GeoController {

    private final GeoService geoService;

    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/countries")
    @Tag(name = "Countries", description = "Endpoints for retrieving countries")
    @Operation(summary = "Get all countries", description = "Returns the list of available countries.")
    @ApiResponse(responseCode = "200", description = "Countries retrieved successfully")
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok(geoService.getAllCountries());
    }

    @GetMapping("/countries/{countryId}/cities")
    @Tag(name = "Cities", description = "Endpoints for retrieving cities")
    @Operation(summary = "Get cities by country", description = "Returns paginated cities belonging to the selected country.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paginated cities retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination or path parameters"),
            @ApiResponse(responseCode = "404", description = "Country not found")
    })
    public ResponseEntity<PagedResponse<City>> getCitiesByCountry(
            @Parameter(description = "ID of the country", example = "1")
            @PathVariable Long countryId,
            @Parameter(description = "Zero-based page index", example = "0")
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {
        return ResponseEntity.ok(geoService.getCitiesByCountry(countryId, page, size));
    }

    @GetMapping("/cities/{cityId}")
    @Tag(name = "Cities", description = "Endpoints for retrieving cities")
    @Operation(summary = "Get city details", description = "Returns detailed information for a specific city.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "City details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    public ResponseEntity<City> getCityById(
            @Parameter(description = "ID of the city", example = "101")
            @PathVariable Long cityId) {
        return ResponseEntity.ok(geoService.getCityById(cityId));
    }
}
