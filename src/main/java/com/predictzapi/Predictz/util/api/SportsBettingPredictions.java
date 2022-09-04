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
        for (Data data: result.data){
            SuperPick pick = new SuperPick();
            pick.setPrediction(data.getPrediction());
            pick.setId(data.getId());
            pick.setDate(data.getDate());
            pick.setMarket_name(data.getMarket_name());
            pick.setCountry(data.getCountry());
            pick.setProbability(data.getProbability());
            pick.setSport_name(data.getSport_name());
            pick.setAway_team(data.getAway_team());
            pick.setHome_team(data.getHome_team());
            pick.setCompetition(data.getCompetition());
            pick.setMarket_id(data.getMarket_id());
            pick.setFixture_status(data.getFixture_status());
            pick.setFixture_id(data.getFixture_id());
            pick.setOdd(data.getOdd());
            pick.setSport_id(data.getSport_id());
            pick.setResults(data.getResults());

            superPicks.add(pick);
        }
        return superPicks;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @lombok.Data
    public static class Result {
        private String status;
        private List<Data> data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @lombok.Data
    public static class Data{
        private String away_team;
        private String competition;
        private String country;
        private String date;
        private long fixture_id;
        private String fixture_status;
        private String home_team;
        private long id;
        private String market_id;
        private String market_name;
        private String odd;
        private String prediction;
        private String probability;
        private String results;
        private String sport_id;
        private String sport_name;
    }
}
