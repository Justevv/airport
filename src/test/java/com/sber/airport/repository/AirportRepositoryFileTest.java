package com.sber.airport.repository;

import com.sber.airport.entity.AirportEntity;
import com.sber.airport.util.Reader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AirportRepositoryFileTest {
    @MockBean
    private Reader reader;
    private static final String AIRPORT = "1,name,output,country,outputCode,inputCode,coordinateX,coordinateY,data1,data2,data3,data4,data5,data6";
    private static final String INCORRECT_AIRPORT = "1";
    private AirportRepositoryFile airportRepositoryFile;

    @BeforeEach
    void init() {
        airportRepositoryFile = new AirportRepositoryFile(reader);
    }

    @Test
    void createAirports_shouldReturnAirportsList() {
        when(reader.readFile()).thenReturn(new ArrayList<>(Collections.singletonList(AIRPORT)));
        List<AirportEntity> airports = airportRepositoryFile.getAirports();
        AirportEntity airportEntity = AirportEntity
                .builder()
                .id(1)
                .name("name")
                .output("output")
                .country("country")
                .outputCode("outputCode")
                .inputCode("inputCode")
                .coordinateX("coordinateX")
                .coordinateY("coordinateY")
                .data1("data1")
                .data2("data2")
                .data3("data3")
                .data4("data4")
                .data5("data5")
                .data6("data6")
                .build();
        Assertions.assertEquals(1, airports.size());
        Assertions.assertEquals(airportEntity, airports.get(0));
    }

    @Test
    void createAirports_shouldReturnEmptyList_whenFileFormatIsIncorrect() {
        when(reader.readFile()).thenReturn(new ArrayList<>(Collections.singletonList(INCORRECT_AIRPORT)));
        List<AirportEntity> airports = airportRepositoryFile.getAirports();
        Assertions.assertEquals(0, airports.size());
    }
}