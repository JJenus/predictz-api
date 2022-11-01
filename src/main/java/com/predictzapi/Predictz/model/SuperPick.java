package com.predictzapi.Predictz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuperPick {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String away_team;
    private String competition;
    private String country;
    private String date;
    private long fixture_id;
    private String fixture_status;
    private String home_team;
    private String market_id;
    private String market_name;
    private String odd;
    private String prediction;
    private String probability;
    private String results;
    private String sport_id;
    private String sport_name;
}
