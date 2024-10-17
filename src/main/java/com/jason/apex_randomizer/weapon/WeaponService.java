package com.jason.apex_randomizer.weapon;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jason.apex_randomizer.ammo.Ammo;
import com.jason.apex_randomizer.ammo.AmmoRepository;


@Service
public class WeaponService {

    private WeaponRepository weaponRepository;
    private AmmoRepository ammoRepository;

    @Autowired
    public WeaponService(WeaponRepository theRepository, AmmoRepository theAmmoRepository){
        weaponRepository = theRepository;
        ammoRepository = theAmmoRepository;
    }

    public List<Weapon> getWeapons(){
        return weaponRepository.findAll();
    }
    public ResponseEntity<?> getWeapon(Long id){
        Weapon theWeapon = getWeapons().stream().filter(weapon -> weapon.getId().equals(id)).findFirst().orElse(null);
        if(theWeapon == null){
             return new ResponseEntity<>("Weapon with the given ID doesn't exist: " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(theWeapon);
    }
    public ResponseEntity<?> addNewWeapon(Weapon theWeapon) {
        Optional<Ammo> optionalAmmo = ammoRepository.findAmmoByName(theWeapon.getAmmoType());
        if(!optionalAmmo.isPresent()){
            return new ResponseEntity<>("The given ammo name doesn't exist: "+ theWeapon.getAmmoType(), HttpStatus.NOT_FOUND);
        }
        Optional<Weapon> optionalWeapon = weaponRepository.findWeaponsByName(theWeapon.getName());
        if(optionalWeapon.isPresent()){
           return new ResponseEntity<>("Weapon with the given name already exists!: " + theWeapon.getName(), HttpStatus.FORBIDDEN);
        }
        Ammo ammo = optionalAmmo.get();
        theWeapon.setAmmo(ammo);
        weaponRepository.save(theWeapon);
        return ResponseEntity.ok(theWeapon);
    }

    public ResponseEntity<?> updateWeapon(Long id, Weapon theWeapon) {
        Optional<Weapon> optionalWeapon = weaponRepository.findById(id);
        if(!optionalWeapon.isPresent()){
            return new ResponseEntity<>("Weapon with the given ID doesn't exist: " + id, HttpStatus.NOT_FOUND);
        }
        Weapon existingWeapon = optionalWeapon.get();

        if(theWeapon.getName() != null){
            existingWeapon.setName(theWeapon.getName());
        }
        if(theWeapon.getAmmoType() != null){
            Optional<Ammo> optionalAmmo = ammoRepository.findAmmoByName(theWeapon.getAmmoType());
            if(!optionalAmmo.isPresent()){
                return ResponseEntity.notFound().build();
            }
            Ammo ammo = optionalAmmo.get();
            existingWeapon.setAmmo(ammo);
        }
        if(theWeapon.getImageURL() != null){
            existingWeapon.setImageURL(theWeapon.getImageURL());
        }
        if(theWeapon.getIsCarePackage() != existingWeapon.getIsCarePackage()){
            existingWeapon.setIsCarePackage(theWeapon.getIsCarePackage());
        }
        weaponRepository.save(existingWeapon);
        return ResponseEntity.ok(existingWeapon);

    }

    public ResponseEntity<?> deleteWeapon(Long id) {
        if(weaponRepository.existsById(id)){
            weaponRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>("Weapon with the given ID doesn't exist: " + id, HttpStatus.NOT_FOUND);
    }
}
