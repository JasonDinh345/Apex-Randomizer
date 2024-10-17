package com.jason.apex_randomizer.ammo;

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
@RequestMapping(path = "api/ammo")
public class AmmoController {
    private AmmoService ammoService;

    @Autowired
    public AmmoController(AmmoService theService){
        ammoService = theService;
    }
    @GetMapping
    public List<Ammo> getAmmo(){
        return ammoService.getAmmo();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAmmo(@PathVariable Long id){
        return ammoService.getAmmo(id);
    }

    @PostMapping
    public ResponseEntity<?> addNewAmmo(@RequestBody Ammo theAmmo){
        return ammoService.addNewAmmo(theAmmo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAmmo(@PathVariable Long id, @RequestBody Ammo theAmmo){
        return ammoService.updateAmmo(id, theAmmo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAmmo(@PathVariable Long id){
        return ammoService.deleteAmmo(id);
    }
}
