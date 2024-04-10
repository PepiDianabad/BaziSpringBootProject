package com.example.springbootapp.Services;

import com.example.springbootapp.Models.City;
import com.example.springbootapp.Models.Airport;
import com.example.springbootapp.Repositories.AirportRepository;
import com.example.springbootapp.Repositories.CityRepository;
import com.example.springbootapp.DTOs.AirportDTO;
import com.example.springbootapp.DTOs.CityDTO;
import com.example.springbootapp.DTOs.CountryDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<AirportDTO> findAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AirportDTO convertToDTO(Airport airport) {
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