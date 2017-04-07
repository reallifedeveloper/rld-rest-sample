package com.reallifedeveloper.sample.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CountryTest {

    @Test
    public void constructor() {
        Country country = new Country("India", "IN", "IND");
        assertThat(country.getName(), is("India"));
        assertThat(country.getAlpha2Code(), is("IN"));
        assertThat(country.getAlpha3Code(), is("IND"));
    }

    @Test
    public void testToString() {
        Country country = new Country("India", "IN", "IND");
        assertThat(country.toString(), is("Country{name=India, alpha2Code=IN, alpha3Code=IND}"));
    }
}
