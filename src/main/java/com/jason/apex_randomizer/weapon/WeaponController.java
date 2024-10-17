package com.jason.apex_randomizer.weapon;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(path = "api/weapons")
public class WeaponController {
    
    private WeaponService weaponService;

    @Autowired
    public WeaponController(WeaponService theService){
        weaponService = theService;
    }
    @GetMapping
    public List<Weapon> getWeapons(){
        return weaponService.getWeapons();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getWeapon(@PathVariable Long id){
        return weaponService.getWeapon(id);
    }
    @PostMapping
    public ResponseEntity<?> addNewWeapon(@RequestBody Weapon theWeapon){
        return weaponService.addNewWeapon(theWeapon);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateWeapon(@PathVariable Long id, @RequestBody Weapon theWeapon){
        return weaponService.updateWeapon(id, theWeapon);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWeapon(@PathVariable Long id){
        return weaponService.deleteWeapon(id);
    }
}
