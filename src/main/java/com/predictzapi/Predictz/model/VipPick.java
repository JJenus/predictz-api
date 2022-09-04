package com.predictzapi.Predictz.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class VipPick {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "home_team")
    private String homeTeam;
    @Column(name = "away_team")
    private String awayTeam;
    @Column(name = "home_team_goals")
    private int homeTeamGoals;
    @Column(name = "away_team_goals")
    private int awayTeamGoals;
    private String competition;
    private String date;

    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreated(){
        created_at = LocalDateTime.now();
        onUpdate();
    }

    @PostPersist
    public void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
