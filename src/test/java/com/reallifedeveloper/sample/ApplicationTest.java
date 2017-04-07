package com.reallifedeveloper.sample;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.reallifedeveloper.sample.domain.CountryService;
import com.reallifedeveloper.sample.infrastructure.GroupKTCountryService;

public class ApplicationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Application application;

    @Before
    public void init() {
        ApplicationProperties properties = new ApplicationProperties();
        properties.setCountryServiceUrl("foo");
        application = new Application(properties);
    }

    @Test
    public void countryService() {
        CountryService countryService = application.countryService();
        assertThat(countryService, notNullValue());
        assertThat(countryService, instanceOf(GroupKTCountryService.class));
    }

    @Test
    public void constructorNullProperties() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("properties must not be null");
        new Application(null);
    }
}
