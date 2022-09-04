package com.predictzapi.Predictz.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class TelegramInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ip;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreated(){
        createdAt = LocalDateTime.now();
    }
}
