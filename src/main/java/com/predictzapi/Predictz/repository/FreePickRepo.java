package com.predictzapi.Predictz.repository;

import com.predictzapi.Predictz.model.FreePick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FreePickRepo extends JpaRepository<FreePick, Long> {
    @Query(value = "Select * from free_pick where DATE(start_date) = :idate", nativeQuery = true)
    List<FreePick> findByGameDate(@Param(value = "idate") String idate);
}
