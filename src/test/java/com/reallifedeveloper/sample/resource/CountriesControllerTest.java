package com.reallifedeveloper.sample.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.reallifedeveloper.sample.domain.Country;
import com.reallifedeveloper.sample.domain.CountryService;
import com.reallifedeveloper.sample.domain.State;
import com.reallifedeveloper.sample.infrastructure.FileCountryService;

public class CountriesControllerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private CountryService countryService = new FileCountryService("json");
    private CountriesController controller = new CountriesController(countryService);

    @Test
    public void countries() throws Exception {
        List<Country> countries = controller.countries();
        assertThat(countries, notNullValue());
        assertThat(countries.size(), is(249));
    }

    @Test
    public void country() throws Exception {
        List<State> states = controller.country("IND");
        assertThat(states, notNullValue());
        assertThat(states.size(), is(36));
    }

    @Test
    public void countryUnknownAlpha3Code() throws Exception {
        List<State> states = controller.country("foo");
        assertThat(states, notNullValue());
        assertThat(states.isEmpty(), is(true));
    }

    @Test
    public void countryNullAlpha3Code() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("alpha3Code must not be null");
        controller.country(null);
    }
}
