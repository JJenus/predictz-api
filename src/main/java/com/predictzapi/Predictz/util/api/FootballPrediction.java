package com.predictzapi.Predictz.util.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.predictzapi.Predictz.model.FreePick;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class FootballPrediction {
    public static final String NAME = "Football betting";
    public final static int limit = 20;
    private String url = "https://football-prediction-api.p.rapidapi.com/api/v2/predictions";
    private Result result;

    public List<FreePick> getFreePicks(String date){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-RapidAPI-Key", "4e7d8bec8cmsha01fa385aca3b94p1f767ejsn6c53b309ac79");
        httpHeaders.add("X-RapidAPI-Host", "football-prediction-api.p.rapidapi.com");

        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);
        RestTemplate temp = new RestTemplate();
        result = temp.exchange(url, HttpMethod.GET, httpEntity, Result.class).getBody();

        System.out.println("Fetched");
        return result.data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @lombok.Data
    public static class Result {
        private List<FreePick> data;
    }
}
