package com.example.springbootapp.Endpoints;

import com.example.springbootapp.Repositories.AirportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v0/airports")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping
    public List<Object> getAllAirports() {
        List<Object[]> airportsData = airportRepository.getAllAirportsWithCountryAndCity();

        return airportsData.stream()
                .map(this::mapToAirportResponse)
                .collect(Collectors.toList());
    }

    private Map<String, Object> mapToAirportResponse(Object[] rowData) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> cityMap = new HashMap<>();
        Map<String, Object> countryMap = new HashMap<>();

        cityMap.put("id", rowData[2]);
        cityMap.put("name", rowData[3]);

        countryMap.put("name", rowData[4]);
        countryMap.put("iso2CountryCode", rowData[5]);
        countryMap.put("iso3CountryCode", rowData[6]);

        response.put("id", rowData[0]);
        response.put("name", rowData[1]);
        response.put("city", cityMap);
        response.put("country", countryMap);
        response.put("iataCode", rowData[7]);
        response.put("icaoCode", rowData[8]);
        response.put("latitude", rowData[9]);
        response.put("longitude", rowData[10]);

        return response;
    }
}