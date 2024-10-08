package com.jason.apex_randomizer.legendClass;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface LegendClassRepository extends JpaRepository<LegendClass, Long>{

    @Query("SELECT lc FROM LegendClass lc WHERE lc.name = ?1")
    Optional<LegendClass> findLegendClassByName(String theName);

} 