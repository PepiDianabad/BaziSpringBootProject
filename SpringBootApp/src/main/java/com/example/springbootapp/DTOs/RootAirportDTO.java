package com.example.springbootapp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RootAirportDTO {
    private Long airportId;
    private String airportName;
    private String airportIataCode;
    private String airportIcaoCode;
    private Double airportLatitude;
    private Double airportLongitude;
    private Long cityId;
    private String cityName;
    private String countryName;
    private String countryIso2Code;
    private String countryIso3Code;

}
