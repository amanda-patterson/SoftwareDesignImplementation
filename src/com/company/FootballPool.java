package com.company;

import java.util.ArrayList;

public class FootballPool {
    ArrayList<Player> defenders = new ArrayList<Player>();
    ArrayList<Player> midfielders = new ArrayList<Player>();
    ArrayList<Player> forwards = new ArrayList<Player>();
    ArrayList<Player> goalies = new ArrayList<Player>();
    //This is just for testing purposes. In Assignment 3 we will have real data loaded automatically or from csv.//
        Player defender1 = new Player("John Smith", "Liverpool", "d");
        Player midfielder1 = new Player("James Guy", "Manchester", "m");
        Player forward1 = new Player("Foot Baller", "Team", "f");
        Player goalie1 = new Player("Andrew Cleats", "AnotherTeam", "g");
    protected FootballPool() {
        defenders.add(defender1);
        midfielders.add(midfielder1);
        forwards.add(forward1);
        goalies.add(goalie1);
    }

}
