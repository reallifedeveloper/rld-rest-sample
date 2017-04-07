package com.reallifedeveloper.sample.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {

    private final String name;
    private final String alpha2Code;
    private final String alpha3Code;

    @JsonCreator
    public Country(@JsonProperty("name") String name, @JsonProperty("alpha2_code") String alpha2Code,
            @JsonProperty("alpha3_code") String alpha3Code) {
        super();
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
    }

    public String getName() {
        return name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    @Override
    public String toString() {
        return "Country{name=" + name + ", alpha2Code=" + alpha2Code + ", alpha3Code=" + alpha3Code + "}";
    }

}
