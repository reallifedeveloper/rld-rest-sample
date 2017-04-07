package com.reallifedeveloper.sample.infrastructure;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.reallifedeveloper.sample.domain.Country;
import com.reallifedeveloper.sample.domain.CountryService;
import com.reallifedeveloper.sample.domain.State;

public class GroupKTCountryService implements CountryService {

    private static final Logger LOG = LoggerFactory.getLogger(GroupKTCountryService.class);

    private final String baseUrl;

    public GroupKTCountryService(String baseUrl) {
        LOG.info("GroupKTCountryService: baseUrl={}", baseUrl);
        if (baseUrl == null) {
            throw new IllegalArgumentException("baseUrl must not be null");
        }
        this.baseUrl = baseUrl;
    }

    @Override
    public List<Country> allCountries() throws IOException {
        final String methodName = "allCountries";
        LOG.debug("{}: Entering", methodName);
        String jsonCountries = jsonAllCountries();
        ObjectMapper objectMapper = new ObjectMapper();
        RestResponseWrapper<Country> countriesResponse =
                objectMapper.readValue(jsonCountries, new TypeReference<RestResponseWrapper<Country>>() {
                });
        LOG.trace("{}: countriesResponse={}", methodName, countriesResponse);
        List<Country> countries = countriesResponse.restResponse.result;
        LOG.trace("{}: countries={}", methodName, countries);
        LOG.debug("{}: Exiting", methodName);
        return countries;
    }

    @Override
    public List<State> statesOfCountry(String alpha3Code) throws IOException {
        final String methodName = "statesOfCountry";
        LOG.debug("{}: Entering, alpha3Code={}", methodName, alpha3Code);
        if (alpha3Code == null) {
            throw new IllegalArgumentException("alpha3Code must not be null");
        }
        String jsonStates = jsonStatesOfCountry(alpha3Code);
        ObjectMapper objectMapper = new ObjectMapper();
        RestResponseWrapper<State> statesResponse =
                objectMapper.readValue(jsonStates, new TypeReference<RestResponseWrapper<State>>() {
                });
        LOG.trace("{}: statesResponse={}", methodName, statesResponse);
        List<State> states = statesResponse.restResponse.result;
        LOG.trace("{}: states={}", methodName, states);
        LOG.debug("{}: Exiting", methodName);
        return states;
    }

    protected String jsonAllCountries() throws IOException {
        final String methodName = "jsonAllCountries";
        RestTemplate restTemplate = new RestTemplate();
        String jsonCountries = restTemplate.getForObject(baseUrl() + "/country/get/all", String.class);
        LOG.trace("{}: jsonCountries={}", methodName, jsonCountries);
        return jsonCountries;
    }

    protected String jsonStatesOfCountry(String alpha3Code) throws IOException {
        final String methodName = "jsonStatesOfCountry";
        RestTemplate restTemplate2 = new RestTemplate();
        String stateUrl = baseUrl() + "/state/get/" + alpha3Code + "/all";
        String jsonStates = restTemplate2.getForObject(stateUrl, String.class);
        LOG.trace("{}: jsonStates={}", methodName, jsonStates);
        return jsonStates;
    }

    protected String baseUrl() {
        return baseUrl;
    }

    private static final class RestResponseWrapper<T> {
        private final RestResponse<T> restResponse;

        @JsonCreator
        RestResponseWrapper(@JsonProperty("RestResponse") RestResponse<T> restResponse) {
            this.restResponse = restResponse;
        }

        @Override
        public String toString() {
            return "RestResponseWrapper{restResponse=" + restResponse + "}";
        }

        private static final class RestResponse<T> {
            private final List<String> messages;
            private final List<T> result;

            @JsonCreator
            RestResponse(@JsonProperty("messages") List<String> messages,
                    @JsonProperty("result") List<T> result) {
                this.messages = messages;
                this.result = result;
            }

            @Override
            public String toString() {
                return "RestResponse{messages=" + messages + ", result=" + result + "}";
            }
        }

    }
}
