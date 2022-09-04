package com.predictzapi.Predictz.repository;

import com.predictzapi.Predictz.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
