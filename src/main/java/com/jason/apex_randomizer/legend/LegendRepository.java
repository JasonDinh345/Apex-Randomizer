package com.jason.apex_randomizer.legend;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LegendRepository extends JpaRepository<Legend, Long>{

    @Query("SELECT l FROM Legend l WHERE l.name = ?1")
    Optional<Legend> findLegendByName(String theName);
} 