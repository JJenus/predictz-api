package com.predictzapi.Predictz.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private String email;
    private String message;
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
