package com.sber.airport.controller;

import com.sber.airport.model.CountryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/countries")
public class Country {
    private static final Logger LOGGER = LogManager.getLogger(Country.class);
    private final CountryModel countryModel;

    public Country(CountryModel countryModel) {
        this.countryModel = countryModel;
    }

    @GetMapping
    public Set<String> getCountries() {
        Set<String> countries = countryModel.getCountries();
        LOGGER.debug("Current list of countries {}", countries);
        return countries;
    }
}
