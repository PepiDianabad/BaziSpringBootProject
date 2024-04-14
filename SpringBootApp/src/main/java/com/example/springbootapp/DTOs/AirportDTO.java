package com.example.springbootapp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirportDTO {
    private Long id;
    private String name;
    private CityDTO city;
    private String iataCode;
    private String icaoCode;
    private Double latitude;
    private Double longitude;
}