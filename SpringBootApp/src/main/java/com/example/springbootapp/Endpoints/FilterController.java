package com.example.springbootapp.Endpoints;

import com.example.springbootapp.Utils.AirportFilter;
import com.example.springbootapp.Repositories.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {

    @Autowired
    private FilterRepository airportFilterRepository;

    @PostMapping("/api/v0/airports/filter")
    public List<?> filterAirports(@RequestBody AirportFilter airportFilter, @RequestBody boolean countryAsRoot) {
        if (countryAsRoot) {
            return airportFilterRepository.formatResponseWithCountryAsRoot(airportFilter);
        } else {
            return airportFilterRepository.formatResponseWithAirportAsRoot(airportFilter);
        }
    }
}
