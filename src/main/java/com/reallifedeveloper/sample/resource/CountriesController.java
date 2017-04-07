package com.reallifedeveloper.sample.resource;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reallifedeveloper.sample.domain.Country;
import com.reallifedeveloper.sample.domain.CountryService;
import com.reallifedeveloper.sample.domain.State;

@RestController
public class CountriesController {

    private CountryService countryService;

    @Autowired
    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping("/countries")
    public List<Country> countries() throws IOException {
        return countryService.allCountries();
    }

    @RequestMapping("/countries/{alpha3Code}")
    public List<State> country(@PathVariable("alpha3Code") String alpha3Code) throws IOException {
        return countryService.statesOfCountry(alpha3Code);
    }
}
