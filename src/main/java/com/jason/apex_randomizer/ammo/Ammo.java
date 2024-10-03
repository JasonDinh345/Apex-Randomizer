package com.jason.apex_randomizer.ammo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "ammo")
public class Ammo {
    @Id
    @SequenceGenerator(
        name = "ammo_sequence",
        sequenceName = "ammo_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "ammo_sequence"
    )
    private Long id;
    private String name;
    private String imageURL;
    private String mythicImageURL;
    
    public Ammo(){
        name = null;
        imageURL = null;
        mythicImageURL = null;
    }

    public Ammo(final String theName, final String theImageURL, final String theMythicImageURL){
        name = theName;
        imageURL = theImageURL;
        mythicImageURL = theMythicImageURL;
    }
    public Long getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getImageURL(){
        return imageURL;
    }
    public String getMythicImageURL(){
        return mythicImageURL;
    }
    public void setName(final String theName){
        name = theName;
    }
    public void setImageURL(final String theImageURL){
        imageURL = theImageURL;
    }
    public void setMythicImageURL(final String theMythicImageURL){
        mythicImageURL = theMythicImageURL;
    }
    public String toString(){
        return name + " with image URL " + imageURL + " and mythic image URL " + mythicImageURL;
    }
    
}
