package com.sber.airport.model;

import com.sber.airport.repository.AirportRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CountryModel {
    private static final Logger LOGGER = LogManager.getLogger(CountryModel.class);
    private final AirportRepository airportRepository;

    public CountryModel(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Set<String> getCountries() {
        Set<String> countryEntities = new HashSet<>();
        airportRepository.getAirports().forEach(x -> countryEntities.add(x.getCountry()));
        LOGGER.debug("Current list of countries {}", countryEntities);
        return countryEntities;
    }
}
