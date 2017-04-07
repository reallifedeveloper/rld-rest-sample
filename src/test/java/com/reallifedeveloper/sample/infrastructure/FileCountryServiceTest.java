package com.reallifedeveloper.sample.infrastructure;

import com.reallifedeveloper.sample.domain.CountryService;

public class FileCountryServiceTest extends GroupKTCountryServiceIT {

    private FileCountryService service = new FileCountryService("json");

    @Override
    protected CountryService service() {
        return service;
    }
}
