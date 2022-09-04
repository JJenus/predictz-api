package com.predictzapi.Predictz.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class ApiLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String apiName;
    private int currentRate;

    public ApiLimit(String apiName, int currentRate, int rateLimit, String status) {
        this.apiName = apiName;
        this.currentRate = currentRate;
        this.rateLimit = rateLimit;
        this.status = status;
    }

    private int rateLimit;
    private String status;
}
