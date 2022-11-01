package com.predictzapi.Predictz.util.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.predictzapi.Predictz.model.SuperPick;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class SportsBettingPredictions {
    public static final String NAME = "Sports betting";
    public final static int limit = 100;
    private String url = "https://sports-betting-predictions.p.rapidapi.com/v1/prediction?sport_id=1";
    private String picks;
    private Result result;

    public String getPicks() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-RapidAPI-Key", "4e7d8bec8cmsha01fa385aca3b94p1f767ejsn6c53b309ac79");
        httpHeaders.add("X-RapidAPI-Host", "sports-betting-predictions.p.rapidapi.com");

        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        RestTemplate temp = new RestTemplate();
        String picks = temp.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
        return picks;
    }

    public List<SuperPick> getSuperPicks(String date){
        this.url += "&date="+date;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-RapidAPI-Key", "4e7d8bec8cmsha01fa385aca3b94p1f767ejsn6c53b309ac79");
        httpHeaders.add("X-RapidAPI-Host", "sports-betting-predictions.p.rapidapi.com");

        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        RestTemplate temp = new RestTemplate();
        result= temp.exchange(url, HttpMethod.GET, httpEntity, Result.class).getBody();
        List<SuperPick> superPicks = new ArrayList<>();
        if (superPicks.size() > 20)
            return superPicks.subList(0, 20);
        return superPicks;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @lombok.Data
    public static class Result {
        private String status;
        private List<SuperPick> data;
    }
}
