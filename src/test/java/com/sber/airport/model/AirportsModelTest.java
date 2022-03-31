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
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AirportsModelTest {
    private static final String COUNTRY = "country";
    private static final String FILTER = "country";
    private static final String WRONG_FILTER = "filter";
    @MockBean
    private AirportRepository airportRepository;
    private AirportEntity airportEntity;
    private List<AirportEntity> airportEntities = new ArrayList<>();
    private AirportsModel airportsModel;

    @BeforeEach
    void createAirport() {
        airportEntity = AirportEntity
                .builder()
                .id(1)
                .country(COUNTRY)
                .build();
        airportEntities.add(airportEntity);
        airportsModel = new AirportsModel(airportRepository);
    }

    @Test
    void getAirports_shouldReturnAirportsList() {
        when(airportRepository.getAirports()).thenReturn(airportEntities);
        List<AirportEntity> airports = airportsModel.getAirports();
        Assertions.assertEquals(1, airports.size());
        Assertions.assertEquals(airportEntity, airports.get(0));
    }

    @Test
    void getAirports_shouldReturnEmptyList_whenAirportsIsNotExist() {
        when(airportRepository.getAirports()).thenReturn(new ArrayList<>());
        List<AirportEntity> airports = airportsModel.getAirports();
        Assertions.assertEquals(0, airports.size());
    }

    @Test
    void getAirports_shouldReturnAirportsList_whenFilterIsCorrect() {
        when(airportRepository.getAirports()).thenReturn(airportEntities);
        List<AirportEntity> airports = airportsModel.getAirports(FILTER);
        Assertions.assertEquals(1, airports.size());
        Assertions.assertEquals(airportEntity, airports.get(0));
    }

    @Test
    void getAirports_shouldReturnEmptyList_whenFilterIsWrong() {
        when(airportRepository.getAirports()).thenReturn(airportEntities);
        List<AirportEntity> airports = airportsModel.getAirports(WRONG_FILTER);
        Assertions.assertEquals(0, airports.size());
    }

}