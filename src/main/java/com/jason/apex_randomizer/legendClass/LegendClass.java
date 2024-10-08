package com.jason.apex_randomizer.legendClass;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "legendClasses")
public class LegendClass {
    @Id
    @SequenceGenerator(
        name = "legendClass_sequence",
        sequenceName = "legendClass_sequence"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "legendClass_seqence"
    )
    private Long id;
    private String name;
    private String imageURL;
    public LegendClass(){
        name = null;
        imageURL = null;
    }
    public LegendClass(final String theName,  final String theImageURL){
        name = theName;
     
        imageURL = theImageURL;
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
    public void setName(final String theName){
        name = theName;
    }
    public void setImageURL(final String theImageURL){
        imageURL = theImageURL;
    }
}
