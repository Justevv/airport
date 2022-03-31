package com.sber.airport.repository;

import com.sber.airport.entity.AirportEntity;

import java.util.List;

public interface AirportRepository {
    List<AirportEntity> getAirports();
}
