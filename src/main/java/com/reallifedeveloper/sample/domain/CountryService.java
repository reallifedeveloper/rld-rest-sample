package com.reallifedeveloper.sample.domain;

import java.io.IOException;
import java.util.List;

public interface CountryService {

    List<Country> allCountries() throws IOException;

    List<State> statesOfCountry(String alpha3Code) throws IOException;

}
