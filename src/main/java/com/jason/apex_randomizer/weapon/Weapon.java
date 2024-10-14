package com.jason.apex_randomizer.weapon;

import com.jason.apex_randomizer.ammo.Ammo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
@Entity
@Table(name = "weapons")
public class Weapon{
    @Id
    @SequenceGenerator(
        name = "weapon_sequence",
        sequenceName =  "weapon_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "weapon_sequence"
    )
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "ammo_id")
    private Ammo ammo;

    @Transient
    private String ammoType;

    private String imageURL;

    @Column(name = "is_care_package", nullable = false)
    private boolean isCarePackage;

    public Weapon(){
        name = null;
        ammo = null;
        imageURL = null;
        isCarePackage = false;
    }
    public Weapon(final String theName, final String theAmmoType, final String theImageURL, final boolean ifInCarePackage){
        name = theName;
        ammoType = theAmmoType;
        imageURL = theImageURL;
        isCarePackage = ifInCarePackage;
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAmmoType(){
        return ammoType;
    }
    public Ammo getAmmo(){
        return ammo;
    }
    public String getImageURL(){
        return imageURL;
    }
    public boolean getIsCarePackage(){
        return isCarePackage;
    }
    public void setName(final String theName){
        name = theName;
    }
    public void setAmmo(final Ammo theAmmo){
        ammo = theAmmo;
    }
    public void setImageURL(final String theImageURL){
        imageURL = theImageURL;
    }
    public void setIsCarePackage(final boolean setIsCarePackage){
        isCarePackage = setIsCarePackage;
    }
}