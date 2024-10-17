package com.jason.apex_randomizer.ammo;

import java.util.List;

import java.util.Optional;
import java.util.NoSuchElementException;
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
    public ResponseEntity<?> getAmmo(Long id) {
        Ammo theAmmo = getAmmo().stream().filter(ammo -> ammo.getID().equals(id)).findFirst().orElse(null);
        if(theAmmo == null){
            return new ResponseEntity<>("Ammo with the given ID doesn't exist: " + id  , HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(theAmmo);
    }

    public ResponseEntity<?> addNewAmmo(Ammo theAmmo) {
        Optional<Ammo> optionalAmmo = ammoRepository.findAmmoByName(theAmmo.getName());
        if(optionalAmmo.isPresent()){
           return new ResponseEntity<>("Ammo already exists: " + theAmmo.getName()  , HttpStatus.FORBIDDEN);
        }
        ammoRepository.save(theAmmo);
        return ResponseEntity.ok(theAmmo);
    }

    public ResponseEntity<?> updateAmmo(Long id, Ammo theAmmo) {
       Ammo existingAmmo;
        try{
			existingAmmo = ammoRepository.findById(id).get();
		}catch(NoSuchElementException e){
			return new ResponseEntity<>("Ammo with the given ID doesn't exist: " + id, HttpStatus.NOT_FOUND);
			
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

    public ResponseEntity<?> deleteAmmo(Long id) {
       if(ammoRepository.existsById(id)){
        ammoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
       }
       return new ResponseEntity<>("Ammo with the given ID doesn't exist: " + id, HttpStatus.NOT_FOUND);
    }


}