package com.reallifedeveloper.sample.infrastructure;

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

public class GroupKTCountryServiceIT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private GroupKTCountryService service = new GroupKTCountryService("http://services.groupkt.com");

    @Test
    public void allCountries() throws Exception {
        List<Country> allCountries = service().allCountries();
        assertThat(allCountries, notNullValue());
        assertThat(allCountries.size(), is(249));
    }

    @Test
    public void indiaShouldHave36States() throws Exception {
        List<State> statesOfIndia = service().statesOfCountry("IND");
        assertThat(statesOfIndia, notNullValue());
        assertThat(statesOfIndia.size(), is(36));
    }

    @Test
    public void unknownCountryShouldGiveEmptyList() throws Exception {
        List<State> statesOfUnknownCountry = service().statesOfCountry("foo");
        assertThat(statesOfUnknownCountry, notNullValue());
        assertThat(statesOfUnknownCountry.isEmpty(), is(true));
    }

    @Test
    public void nullCountryShouldThrowException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("alpha3Code must not be null");
        service().statesOfCountry(null);
    }

    @Test
    public void constructorNullBaseUrlShouldThrowException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("baseUrl must not be null");
        new GroupKTCountryService(null);
    }

    protected CountryService service() {
        return service;
    }
}
