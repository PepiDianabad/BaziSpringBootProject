package com.example.springbootapp.Repositories;

import com.example.springbootapp.Models.Airport;
import com.example.springbootapp.Utils.AirportFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.springbootapp.DTOs.RootAirportCountryDTO;
import com.example.springbootapp.DTOs.RootAirportDTO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRepository extends JpaRepository<Airport, Long> {
    @Query(value = "SELECT c.name, c.iso2country_code, c.iso3country_code, " +
            "ci.id, ci.name, " +
            "a.id, a.name, a.iata_code, a.icao_code, " +
            "a.latitude, a.longitude " +
            "FROM Country c " +
            "JOIN City ci ON c.id = ci.country_id " +
            "LEFT JOIN Airport a ON ci.id = a.city_id " +
            "WHERE (:airportFilter.countryIso2Codes IS NULL OR c.iso2_country_code IN :airportFilter.countryIso2Codes) " +
            "AND (:airportFilter.cityIds IS NULL OR ci.id IN :airportFilter.cityIds) " +
            "AND (:airportFilter.airportIcaoCodes IS NULL OR a.icao_code IN :airportFilter.airportIcaoCodes) " +
            "AND (:airportFilter.airportNames IS NULL OR a.name IN :airportFilter.airportNames)",
            nativeQuery = true)
    List<RootAirportCountryDTO> formatResponseWithCountryAsRoot(AirportFilter airportFilter);

    @Query(value = "SELECT a.id, a.name, a.iata_code, a.icao_code, " +
            "a.latitude, a.longitude, " +
            "ci.id, ci.name, " +
            "c.name, c.iso2_country_code, c.iso3_country_code " +
            "FROM Airport a " +
            "JOIN City ci ON a.city_id = ci.id " +
            "JOIN Country c ON ci.country_id = c.id " +
            "WHERE (:airportFilter.countryIso2Codes IS NULL OR c.iso2_country_code IN :airportFilter.countryIso2Codes) " +
            "AND (:airportFilter.cityIds IS NULL OR ci.id IN :airportFilter.cityIds) " +
            "AND (:airportFilter.airportIcaoCodes IS NULL OR a.icao_code IN :airportFilter.airportIcaoCodes) " +
            "AND (:airportFilter.airportNames IS NULL OR a.name IN :airportFilter.airportNames)",
            nativeQuery = true)
    List<RootAirportDTO> formatResponseWithAirportAsRoot(AirportFilter airportFilter);
}