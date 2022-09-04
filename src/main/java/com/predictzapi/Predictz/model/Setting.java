package com.predictzapi.Predictz.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String telegramLink;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreated(){
        createdAt = LocalDateTime.now();
        onUpdate();
    }

    @PostPersist
    public void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
