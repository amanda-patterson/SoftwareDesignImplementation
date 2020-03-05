package com.company;

import java.util.ArrayList;

public class Team {
    //user who created the team
    String owner;
    String name;

    //Arrays of players on team by position
    Player[] defenderArray = new Player[4]; //how many of each position I forget?
    Player[] midfielderArray = new Player[3];
    Player[] forwardArray = new Player[3];
    Player goalie;

    Team(String myName){
        name = myName;
    }


    //Recorded wins/loses of the team by week
    ArrayList<Integer> teamRecord;
}
