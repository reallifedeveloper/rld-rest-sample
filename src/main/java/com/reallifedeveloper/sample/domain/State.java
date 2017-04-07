package com.reallifedeveloper.sample.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class State {

    private final String name;
    private final String abbreviation;
    private final String area;
    private final String largestCity;
    private final String capital;

    @JsonCreator
    public State(@JsonProperty("name") String name, @JsonProperty("abbr") String abbreviation,
            @JsonProperty("area") String area, @JsonProperty("largest_city") String largestCity,
            @JsonProperty("capital") String capital) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.area = area;
        this.largestCity = largestCity;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Double getAreaSqKm() {
        if (area != null && area.endsWith("SKM")) {
            return Double.parseDouble(area.substring(0, area.indexOf("SKM")));
        } else {
            return null;
        }
    }

    public String getLargestCity() {
        return largestCity;
    }

    public String getCapital() {
        return capital;
    }

    @Override
    public String toString() {
        return "State{name=" + name + ", abbreviation=" + abbreviation + ", area=" + area + ", largestCity="
                + largestCity + ", capital=" + capital + "}";
    }

}
