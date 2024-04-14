package com.example.springbootapp.Repositories;

import com.example.springbootapp.Models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, String> {
    Optional<Country> findByIso2CountryCode(String iso2CountryCode);

    Optional<Country> findByIso3CountryCode(String iso3CountryCode);
}
