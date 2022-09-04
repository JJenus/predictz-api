package com.predictzapi.Predictz.repository;

import com.predictzapi.Predictz.model.SuperPick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuperPickRepo extends JpaRepository<SuperPick, Long> {
    @Query(value = "Select * from super_pick where DATE(date) = :idate", nativeQuery = true)
    List<SuperPick> findByGameDate(@Param(value = "idate") String idate);
}
