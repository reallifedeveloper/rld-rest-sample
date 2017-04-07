package com.reallifedeveloper.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.reallifedeveloper.sample.domain.CountryService;
import com.reallifedeveloper.sample.infrastructure.GroupKTCountryService;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class Application {

    private final ApplicationProperties properties;

    @Autowired
    public Application(ApplicationProperties properties) {
        if (properties == null) {
            throw new IllegalStateException("properties must not be null");
        }
        this.properties = properties;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CountryService countryService() {
        return new GroupKTCountryService(properties.getCountryServiceUrl());
    }
}
