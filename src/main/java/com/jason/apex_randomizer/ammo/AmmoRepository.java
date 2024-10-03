package com.jason.apex_randomizer.ammo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AmmoRepository extends JpaRepository<Ammo, Long>  {

    @Query("SELECT a FROM Ammo a WHERE a.name = ?1")
    Optional<Ammo> findAmmoByName(String theName);
} 