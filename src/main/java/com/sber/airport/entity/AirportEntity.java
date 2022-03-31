package com.sber.airport.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportEntity {
    private long id;
    private String name;
    private String output;
    private String country;
    private String outputCode;
    private String inputCode;
    private String coordinateX;
    private String coordinateY;
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private String data5;
    private String data6;
    private String data7;
}
