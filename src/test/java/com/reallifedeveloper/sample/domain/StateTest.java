package com.reallifedeveloper.sample.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateTest {

    private static final double DELTA = 0.0000001;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void constructor() {
        State state = new State("Andhra Pradesh", "AP", "160205SKM", "Visakhapatnam", "Hyderabad, India");
        assertThat(state.getName(), is("Andhra Pradesh"));
        assertThat(state.getAbbreviation(), is("AP"));
        assertThat(state.getAreaSqKm(), Matchers.closeTo(160205, DELTA));
        assertThat(state.getLargestCity(), is("Visakhapatnam"));
        assertThat(state.getCapital(), is("Hyderabad, India"));
    }

    @Test
    public void areaNull() {
        State state = new State("Andhra Pradesh", "AP", null, "Visakhapatnam", "Hyderabad, India");
        assertThat(state.getAreaSqKm(), nullValue());
    }

    @Test
    public void areaUnknownFormat() {
        State state = new State("Andhra Pradesh", "AP", "foo", "Visakhapatnam", "Hyderabad, India");
        assertThat(state.getAreaSqKm(), nullValue());
    }

    @Test
    public void areaIllegalNumber() {
        State state = new State("Andhra Pradesh", "AP", "fooSKM", "Visakhapatnam", "Hyderabad, India");
        expectedException.expect(NumberFormatException.class);
        expectedException.expectMessage("For input string: \"foo\"");
        state.getAreaSqKm();
    }

    @Test
    public void testToString() {
        State state = new State("Andhra Pradesh", "AP", "160205SKM", "Visakhapatnam", "Hyderabad, India");
        assertThat(state.toString(),
                is("State{name=Andhra Pradesh, abbreviation=AP, area=160205SKM, largestCity=Visakhapatnam, "
                        + "capital=Hyderabad, India}"));
    }
}
