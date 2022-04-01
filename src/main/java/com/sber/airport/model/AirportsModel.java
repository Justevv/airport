package com.sber.airport.model;

import com.sber.airport.entity.AirportEntity;
import com.sber.airport.repository.AirportRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AirportsModel {
    private static final Logger LOGGER = LogManager.getLogger(AirportsModel.class);
    private final AirportRepository airportRepository;

    public AirportsModel(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<AirportEntity> getAirports() {
        List<AirportEntity> airportEntities = airportRepository.getAirports();
        LOGGER.debug("Current list of airport entities {}", airportEntities);
        return airportEntities;
    }

    public List<AirportEntity> getAirports(String countryBundle) {
        LOGGER.debug("Input filter for airports {}", countryBundle);
        List<AirportEntity> airportEntities = new ArrayList<>();
        airportRepository.getAirports().stream().filter(x -> parseCountries(countryBundle).contains(x.getCountry())).forEach(airportEntities::add);
        LOGGER.debug("Current list of filtered airport entities {}", airportEntities);
        return airportEntities;
    }

    private List<String> parseCountries(String countryBundle) {
        return Arrays.asList(countryBundle.split(","));
    }
}
