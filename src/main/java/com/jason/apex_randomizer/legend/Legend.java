package com.jason.apex_randomizer.legend;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "legends")
public class Legend {
    @Id
    @SequenceGenerator(
        name = "legend_sequence",
        sequenceName = "legend_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "legend_sequence"
    )
    private Long id;
    private String name;
    private String className;

    public Legend(){
        
        name = "unknown";
        className = "unknown";
    }
    public Legend(final String theName, final String theClassName){
       
        name = theName;
        className = theClassName;
    }
    public Long getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getClassName(){
        return className;
    }
    public void setID(final Long theID){
        id = theID;
    }
    public void setName(final String theName){
        name = theName;
    }
    public void setClassName(final String theClassName){
        className = theClassName;
    }
    public String toString(){
        return name + " is a " + className;
    }
    
}
