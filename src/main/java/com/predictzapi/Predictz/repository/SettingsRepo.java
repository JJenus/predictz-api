package com.predictzapi.Predictz.repository;

import com.predictzapi.Predictz.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepo extends JpaRepository<Setting, Long> {
}
