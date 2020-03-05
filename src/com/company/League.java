package com.company;

import java.util.ArrayList;

public class League {
    // instance variable
//    Team[] teams = new Team[16];
    ArrayList<Team> teams = new ArrayList<Team>();
    FootballPool myPool = new FootballPool();
    String name;
    String owner;
    ArrayList<Integer> draftOrder;
    int open = 1; //1 means teams can be added, 0 means no more teams allowed

    //This is just for testing. Teams will not be added this way in Assignment 3.
    Team team0 = new Team("Jerry");
    Team team1 = new Team("Lakers") ;
    Team team2 = new Team("WinnersOnly");
    Team team3 = new Team("ILoveSoccer");
    protected League() {
        teams.add(team0);
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
    }

}