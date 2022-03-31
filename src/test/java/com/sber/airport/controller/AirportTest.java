package com.sber.airport.controller;

import com.sber.airport.entity.AirportEntity;
import com.sber.airport.model.AirportsModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AirportTest {
    private static final String COUNTRY = "country";
    private static final String FILTER = "country";
    private static final String WRONG_FILTER = "filter";
    @MockBean
    private AirportsModel airportsModel;
    private AirportEntity airportEntity;
    private List<AirportEntity> airportEntities = new ArrayList<>();
    private Airport airport;

    @BeforeEach
    void init() {
        airportEntity = AirportEntity
                .builder()
                .id(1)
                .country(COUNTRY)
                .build();
        airportEntities.add(airportEntity);
        airport = new Airport(airportsModel);
    }

    @Test
    void getAirports_shouldReturnAirportsList() {
        when(airportsModel.getAirports()).thenReturn(airportEntities);
        List<AirportEntity> airports = airport.getAirports();
        Assertions.assertEquals(1, airports.size());
        Assertions.assertEquals(airportEntity, airports.get(0));
    }

    @Test
    void getAirports_shouldReturnEmptyList_whenAirportsIsNotExist() {
        when(airportsModel.getAirports()).thenReturn(new ArrayList<>());
        List<AirportEntity> airports = airport.getAirports();
        Assertions.assertEquals(0, airports.size());
    }

    @Test
    void getAirports_shouldReturnAirportsList_whenFilterIsCorrect() {
        when(airportsModel.getAirports(FILTER)).thenReturn(airportEntities);
        List<AirportEntity> airports = airport.getAirports(FILTER);
        Assertions.assertEquals(1, airports.size());
        Assertions.assertEquals(airportEntity, airports.get(0));
    }

    @Test
    void getAirports_shouldReturnEmptyList_whenFilterIsWrong() {
        when(airportsModel.getAirports()).thenReturn(airportEntities);
        List<AirportEntity> airports = airport.getAirports(WRONG_FILTER);
        Assertions.assertEquals(0, airports.size());
    }
}