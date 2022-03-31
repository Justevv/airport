package com.sber.airport.model;

import com.sber.airport.entity.AirportEntity;
import com.sber.airport.repository.AirportRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CountryModelTest {
    private static final String COUNTRY = "country";
    @MockBean
    private AirportRepository airportRepository;
    private CountryModel countryModel;

    @BeforeEach
    void init() {
        countryModel = new CountryModel(airportRepository);
    }

    @Test
    void getCountries_shouldReturnCountryList() {
        when(airportRepository.getAirports()).thenReturn(new ArrayList<>(Collections.singletonList(AirportEntity.builder().country(COUNTRY).build())));
        Set<String> countries = countryModel.getCountries();
        Assertions.assertEquals(1, countries.size());
        Assertions.assertTrue(countries.contains(COUNTRY));
    }

    @Test
    void getCountries_shouldReturnEmptyList_whenCountriesIsNotExist() {
        when(airportRepository.getAirports()).thenReturn(new ArrayList<>());
        Set<String> countries = countryModel.getCountries();
        Assertions.assertEquals(0, countries.size());
    }


}