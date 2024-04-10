package com.example.springbootapp.Utils;

import java.util.HashSet;
import java.util.Set;

public class AirportFilter {
    public Set<String> countryIso2Codes;
    public Set<Long> cityIds;
    public Set<String> airportIcaoCodes;
    public Set<String> airportNames;

    public AirportFilter() {
        this.countryIso2Codes = new HashSet<>();
        this.cityIds = new HashSet<>();
        this.airportIcaoCodes = new HashSet<>();
        this.airportNames = new HashSet<>();
    }

    public Set<String> getCountryIso2Codes() {
        return countryIso2Codes;
    }

    public void setCountryIso2Codes(Set<String> countryIso2Codes) {
        this.countryIso2Codes = countryIso2Codes;
    }

    public Set<Long> getCityIds() {
        return cityIds;
    }

    public void setCityIds(Set<Long> cityIds) {
        this.cityIds = cityIds;
    }

    public Set<String> getAirportIcaoCodes() {
        return airportIcaoCodes;
    }

    public void setAirportIcaoCodes(Set<String> airportIcaoCodes) {
        this.airportIcaoCodes = airportIcaoCodes;
    }

    public Set<String> getAirportNames() {
        return airportNames;
    }

    public void setAirportNames(Set<String> airportNames) {
        this.airportNames = airportNames;
    }
}
