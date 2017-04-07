package com.reallifedeveloper.sample;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("rld-rest-sample")
public class ApplicationProperties {

    private String countryServiceUrl;

    public String getCountryServiceUrl() {
        return countryServiceUrl;
    }

    public void setCountryServiceUrl(String countryServiceUrl) {
        this.countryServiceUrl = countryServiceUrl;
    }

}
