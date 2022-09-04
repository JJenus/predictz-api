package com.predictzapi.Predictz.repository;

import com.predictzapi.Predictz.model.TelegramInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramRepo extends JpaRepository<TelegramInteraction, Long> {
}
