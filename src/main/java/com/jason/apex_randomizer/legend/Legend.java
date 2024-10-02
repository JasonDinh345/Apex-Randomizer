package com.jason.apex_randomizer.legend;

public class Legend {
    private Long myID;
    private String myName;
    private String myClassName;

    public Legend(){
        myID = 1l;
        myName = "unknown";
        myClassName = "unknown";
    }
    public Legend(final String theName, final String theClassName){
        myID = 1l;
        myName = theName;
        myClassName = theClassName;
    }
    public Long getID(){
        return myID;
    }
    public String getName(){
        return myName;
    }
    public String getClassName(){
        return myClassName;
    }
    public void setID(final Long theID){
        myID = theID;
    }
    public void setName(final String theName){
        myName = theName;
    }
    public void setClassName(final String theClassName){
        myClassName = theClassName;
    }
    
}
