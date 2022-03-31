package com.sber.airport.controller;

import com.sber.airport.model.CountryModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CountryTest {
    private static final String COUNTRY = "country";
    @MockBean
    private CountryModel countryModel;
    private Set<String> countries = new HashSet<>();
    private Country country;

    @BeforeEach
    void init() {
        country = new Country(countryModel);
    }

    @Test
    void getCountries_shouldReturnCountryList() {
        countries.add(COUNTRY);
        when(countryModel.getCountries()).thenReturn(countries);
        Set<String> countries = country.getCountries();
        Assertions.assertTrue(countries.contains(COUNTRY));
        Assertions.assertEquals(1, countries.size());
    }

    @Test
    void getCountries_shouldReturnEmptyList_whenCountriesIsNotExist() {
        when(countryModel.getCountries()).thenReturn(countries);
        Set<String> countries = country.getCountries();
        Assertions.assertEquals(0, countries.size());
    }
}