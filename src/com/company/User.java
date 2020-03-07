package com.company;

import java.util.ArrayList;

public class User {

    protected String name;
    protected int privateID;
    protected ArrayList<String> teams;

    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }
    public void setPrivateID(int id){
        privateID = id;
    }
    public int getPrivateID(){
        return privateID;
    }
    //Please note that more operations will be added in Assignment 3//
    public void createNewTeam(){}
    public void draftPlayer(){}
}
