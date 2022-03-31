package com.sber.airport.controller;

import com.sber.airport.entity.AirportEntity;
import com.sber.airport.model.AirportsModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class Airport {
    private static final Logger LOGGER = LogManager.getLogger(Airport.class);
    private final AirportsModel airportsModel;

    public Airport(AirportsModel airportsModel) {
        this.airportsModel = airportsModel;
    }

    @GetMapping
    public List<AirportEntity> getAirports() {
        List<AirportEntity> airports = airportsModel.getAirports();
        LOGGER.debug("Current list of airport entities {}", airports);
        return airports;
    }

    @GetMapping("{country}")
    public List<AirportEntity> getAirports(@PathVariable String country) {
        LOGGER.debug("Input filter for airports {}", country);
        List<AirportEntity> airports = airportsModel.getAirports(country);
        LOGGER.debug("Current list of filtered airport entities {}", airports);
        return airports;
    }


}


