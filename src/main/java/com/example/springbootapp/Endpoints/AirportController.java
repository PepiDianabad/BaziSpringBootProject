package com.example.springbootapp.Endpoints;

import com.example.springbootapp.DTOs.AirportDTO;
import com.example.springbootapp.DTOs.CityDTO;
import com.example.springbootapp.DTOs.CountryDTO;
import com.example.springbootapp.Models.Airport;
import com.example.springbootapp.Models.City;
import com.example.springbootapp.Models.Country;
import com.example.springbootapp.Repositories.AirportRepository;
import com.example.springbootapp.Repositories.CountryRepository;
import com.example.springbootapp.Utils.AirportFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<AirportDTO> findFilteredAirports(boolean countryAsRoot, AirportFilter airportFilter, int page, int size) {
        Page<Airport> airportPage;
        if (countryAsRoot) {
            airportPage = filterWithCountryAsRoot(airportFilter, page, size);
        } else {
            airportPage = filterWithAirportAsRoot(airportFilter, page, size);
        }
        return airportPage.getContent().stream().map(this::convertToDTOFiltered).collect(Collectors.toList());
    }

    private Page<Airport> filterWithCountryAsRoot(AirportFilter airportFilter, int page, int size) {
        List<Airport> filteredAirports = new ArrayList<>();

        for (String countryIso2Code : airportFilter.getCountryIso2Codes()) {
            Country country = CountryRepository.findByIso2CountryCode(countryIso2Code);
            if (country != null) {
                for (City city : country.getCities()) {
                    for (Airport airport : city.getAirports()) {
                        if (applyFilters(airport, airportFilter)) {
                            filteredAirports.add(airport);
                        }
                    }
                }
            }
        }

        return sliceList(filteredAirports, page, size);
    }


    private Page<Airport> filterWithAirportAsRoot(AirportFilter airportFilter, int page, int size) {
        List<Airport> filteredAirports = new ArrayList<>();

        for (Airport airport : airportRepository.findAll()) {
            if (applyFilters(airport, airportFilter)) {
                filteredAirports.add(airport);
            }
        }

        return sliceList(filteredAirports, page, size);
    }

    private Page<Airport> sliceList(List<Airport> airports, int page, int size) {
        int start = Math.min(page * size, airports.size());
        int end = Math.min((page + 1) * size, airports.size());
        return new PageImpl<>(airports.subList(start, end), PageRequest.of(page, size), airports.size());
        //tuka trqq se napravi implementaciq na page-ovete
    }

    private boolean applyFilters(Airport airport, AirportFilter airportFilter) {
        boolean matchesCountry = airportFilter.getCountryIso2Codes().isEmpty() || airportFilter.getCountryIso2Codes().contains(airport.getCity().getCountry().getIso2CountryCode());
        boolean matchesCity = airportFilter.getCityIds().isEmpty() || airportFilter.getCityIds().contains(airport.getCity().getId());
        boolean matchesAirportIcao = airportFilter.getAirportIcaoCodes().isEmpty() || airportFilter.getAirportIcaoCodes().contains(airport.getIcaoCode());
        boolean matchesAirportName = airportFilter.getAirportNames().isEmpty() || airportFilter.getAirportNames().contains(airport.getName());
        return matchesCountry && matchesCity && matchesAirportIcao && matchesAirportName;
    }

    private AirportDTO convertToDTOFiltered(Airport airport) {
        AirportDTO dto = new AirportDTO();
        dto.setId(airport.getId());
        dto.setName(airport.getName());
        dto.setIataCode(airport.getIataCode());
        dto.setIcaoCode(airport.getIcaoCode());
        dto.setLatitude(airport.getLatitude());
        dto.setLongitude(airport.getLongitude());

        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(airport.getCity().getId());
        cityDTO.setName(airport.getCity().getName());

        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(airport.getCity().getCountry().getName());
        countryDTO.setIso2CountryCode(airport.getCity().getCountry().getIso2CountryCode());
        countryDTO.setIso3CountryCode(airport.getCity().getCountry().getIso3CountryCode());

        cityDTO.setCountry(countryDTO);
        dto.setCity(cityDTO);

        return dto;
    }
}

