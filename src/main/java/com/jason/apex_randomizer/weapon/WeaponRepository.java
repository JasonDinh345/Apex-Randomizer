package com.jason.apex_randomizer.weapon;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface WeaponRepository extends JpaRepository<Weapon, Long>{
    
    @Query("SELECT w FROM Weapon w WHERE w.name = ?1")
    Optional<Weapon> findWeaponsByName(String theName);
}
