package com.reallifedeveloper.sample.infrastructure;

import java.io.IOException;

import com.reallifedeveloper.tools.test.TestUtil;

public class FileCountryService extends GroupKTCountryService {

    public FileCountryService(String baseUrl) {
        super(baseUrl);
    }

    @Override
    protected String jsonAllCountries() throws IOException {
        return TestUtil.readResource(baseUrl() + "/all_countries.json");
    }

    @Override
    protected String jsonStatesOfCountry(String alpha3Code) throws IOException {
        return TestUtil.readResource(baseUrl() + "/states_" + alpha3Code + ".json");
    }

}
