package com.example.geoapi.service;

import com.example.geoapi.exception.ResourceNotFoundException;
import com.example.geoapi.model.City;
import com.example.geoapi.model.Country;
import com.example.geoapi.model.PagedResponse;
import com.example.geoapi.repository.InMemoryGeoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoService {

    private final InMemoryGeoRepository repository;

    public GeoService(InMemoryGeoRepository repository) {
        this.repository = repository;
    }

    public List<Country> getAllCountries() {
        return repository.findAllCountries();
    }

    public PagedResponse<City> getCitiesByCountry(Long countryId, int page, int size) {
        repository.findCountryById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + countryId));

        List<City> matchingCities = repository.findCitiesByCountryId(countryId);
        long totalRecords = matchingCities.size();
        int totalPages = totalRecords == 0 ? 0 : (int) Math.ceil((double) totalRecords / size);

        int fromIndex = Math.min(page * size, matchingCities.size());
        int toIndex = Math.min(fromIndex + size, matchingCities.size());
        List<City> pageItems = matchingCities.subList(fromIndex, toIndex);

        return new PagedResponse<>(
                pageItems,
                totalRecords,
                page,
                size,
                totalPages
        );
    }

    public City getCityById(Long cityId) {
        return repository.findCityById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id " + cityId));
    }
}
