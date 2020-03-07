package com.company;

import java.util.ArrayList;

public class Team {
    //user who created the team
    private String owner;
    private String name;

    //Arrays of players on team by position
    private Player[] defenderArray = new Player[4]; //how many of each position I forget?
    private Player[] midfielderArray = new Player[3];
    private Player[] forwardArray = new Player[3];
    private Player goalie;

    Team(String myName){
        name = myName;
    }

    public String getName(){
        return name;
    }
    public String getOwner(){
        return owner;
    }


    //Recorded wins/loses of the team by week
    ArrayList<Integer> teamRecord;
}
