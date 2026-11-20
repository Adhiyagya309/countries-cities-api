package com.example.geoapi.repository;

import com.example.geoapi.model.City;
import com.example.geoapi.model.Country;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryGeoRepository {

    private final Map<Long, Country> countries = new ConcurrentHashMap<>();
    private final Map<Long, City> cities = new ConcurrentHashMap<>();

    public InMemoryGeoRepository() {
        seedData();
    }

    private void seedData() {
        List<Country> initialCountries = List.of(
                new Country(1L, "India"),
                new Country(2L, "United States"),
                new Country(3L, "Japan")
        );
        initialCountries.forEach(c -> countries.put(c.id(), c));

        List<City> initialCities = List.of(
                new City(101L, "Bengaluru", 1L, 13600000L, "Karnataka", "Asia/Kolkata"),
                new City(102L, "Mumbai", 1L, 21300000L, "Maharashtra", "Asia/Kolkata"),
                new City(103L, "New Delhi", 1L, 32900000L, "Delhi", "Asia/Kolkata"),
                new City(104L, "Chennai", 1L, 11700000L, "Tamil Nadu", "Asia/Kolkata"),
                new City(105L, "Hyderabad", 1L, 10800000L, "Telangana", "Asia/Kolkata"),
                new City(106L, "Pune", 1L, 7100000L, "Maharashtra", "Asia/Kolkata"),
                new City(107L, "Kolkata", 1L, 15300000L, "West Bengal", "Asia/Kolkata"),
                new City(108L, "Jaipur", 1L, 4200000L, "Rajasthan", "Asia/Kolkata"),
                new City(109L, "Ahmedabad", 1L, 8600000L, "Gujarat", "Asia/Kolkata"),
                new City(110L, "Kochi", 1L, 3400000L, "Kerala", "Asia/Kolkata"),
                new City(111L, "Chandigarh", 1L, 1200000L, "Chandigarh", "Asia/Kolkata"),
                new City(112L, "Indore", 1L, 3300000L, "Madhya Pradesh", "Asia/Kolkata"),

                new City(201L, "New York", 2L, 8336817L, "New York", "America/New_York"),
                new City(202L, "Seattle", 2L, 749256L, "Washington", "America/Los_Angeles"),
                new City(203L, "Austin", 2L, 979882L, "Texas", "America/Chicago"),
                new City(204L, "Chicago", 2L, 2665039L, "Illinois", "America/Chicago"),

                new City(301L, "Tokyo", 3L, 14094034L, "Kanto", "Asia/Tokyo"),
                new City(302L, "Osaka", 3L, 2752412L, "Kansai", "Asia/Tokyo"),
                new City(303L, "Kyoto", 3L, 1448964L, "Kansai", "Asia/Tokyo")
        );
        initialCities.forEach(city -> cities.put(city.id(), city));
    }

    public List<Country> findAllCountries() {
        return countries.values().stream()
                .sorted(Comparator.comparing(Country::id))
                .toList();
    }

    public Optional<Country> findCountryById(Long countryId) {
        return Optional.ofNullable(countries.get(countryId));
    }

    public List<City> findCitiesByCountryId(Long countryId) {
        return cities.values().stream()
                .filter(city -> city.countryId().equals(countryId))
                .sorted(Comparator.comparing(City::id))
                .toList();
    }

    public Optional<City> findCityById(Long cityId) {
        return Optional.ofNullable(cities.get(cityId));
    }
}
