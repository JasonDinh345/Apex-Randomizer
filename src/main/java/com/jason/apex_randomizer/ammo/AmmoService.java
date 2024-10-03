package com.jason.apex_randomizer.ammo;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AmmoService{

    private AmmoRepository ammoRepository;

    @Autowired
    public AmmoService(AmmoRepository theAmmoRepository) {
        ammoRepository = theAmmoRepository;
    }

    public List<Ammo> getAmmo() {
        return ammoRepository.findAll();
    }
    public Ammo getAmmo(Long id) {
        return this.getAmmo().stream().filter(ammo -> ammo.getID().equals(id)).findFirst().orElse(null);
    }

    public ResponseEntity<Ammo> addNewAmmo(Ammo theAmmo) {
        Optional<Ammo> optionalAmmo = ammoRepository.findAmmoByName(theAmmo.getName());
        if(optionalAmmo.isPresent()){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        ammoRepository.save(theAmmo);
        return ResponseEntity.ok(theAmmo);
    }

    public ResponseEntity<Ammo> updateAmmo(Long id, Ammo theAmmo) {
        Ammo existingAmmo = getAmmo(id);
        if(existingAmmo == null){
            return  ResponseEntity.notFound().build();
        }
        if(theAmmo.getName() != null){
            existingAmmo.setName(theAmmo.getName());
        }
        if(theAmmo.getImageURL() != null){
            existingAmmo.setImageURL(theAmmo.getImageURL());
        }
        if(theAmmo.getMythicImageURL() != null){
            existingAmmo.setMythicImageURL(theAmmo.getMythicImageURL());
        }
        ammoRepository.save(existingAmmo);
        return ResponseEntity.ok(existingAmmo);
    }

    public ResponseEntity<Ammo> deleteAmmo(Long id) {
       if(ammoRepository.existsById(id)){
        ammoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }


}