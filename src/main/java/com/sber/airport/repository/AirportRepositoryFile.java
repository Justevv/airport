package com.sber.airport.repository;

import com.sber.airport.entity.AirportEntity;
import com.sber.airport.util.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AirportRepositoryFile implements AirportRepository {
    private static final Logger LOGGER = LogManager.getLogger(AirportRepositoryFile.class);
    Reader reader;

    public AirportRepositoryFile(Reader reader) {
        this.reader = reader;
    }

    @Override
    public List<AirportEntity> getAirports() {
        List<AirportEntity> airportEntities = new ArrayList<>();
        for (String airportRecord : reader.readFile()) {
            String[] airport = airportRecord.split(",");
            if (airport.length == 14) {
                AirportEntity airportEntity = AirportEntity
                        .builder()
                        .id(Long.parseLong(airport[0]))
                        .name(airport[1])
                        .output(airport[2])
                        .country((airport[3]))
                        .outputCode(airport[4])
                        .inputCode(airport[5])
                        .coordinateX(airport[6])
                        .coordinateY(airport[7])
                        .data1(airport[8])
                        .data2(airport[9])
                        .data3(airport[10])
                        .data4(airport[11])
                        .data5(airport[12])
                        .data6(airport[13])
                        .build();
                airportEntities.add(airportEntity);
            } else {
                LOGGER.warn("Current record has incorrect format {}", airportRecord);
            }
        }
        LOGGER.debug("Current list of airport entities {}", airportEntities);
        return airportEntities;
    }

}
