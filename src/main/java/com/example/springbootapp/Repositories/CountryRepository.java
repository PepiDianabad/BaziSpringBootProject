package com.example.springbootapp.Repositories;

import com.example.springbootapp.Models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    static Country findByIso2CountryCode(String iso2CountryCode){

        //nqkva logika...
        return null;
    }
}
