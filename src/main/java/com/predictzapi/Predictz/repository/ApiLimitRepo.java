package com.predictzapi.Predictz.repository;

import com.predictzapi.Predictz.model.ApiLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLimitRepo extends JpaRepository<ApiLimit, Long> {
    ApiLimit findByApiName(String name);
}
