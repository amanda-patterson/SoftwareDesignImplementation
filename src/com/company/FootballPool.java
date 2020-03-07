package com.company;

import java.util.ArrayList;

public class FootballPool {
    private ArrayList<Player> defenders = new ArrayList<Player>();
    private ArrayList<Player> midfielders = new ArrayList<Player>();
    private ArrayList<Player> forwards = new ArrayList<Player>();
    private ArrayList<Player> goalies = new ArrayList<Player>();
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
    public ArrayList<Player> getDefenders(){
        return defenders;
    }
    public ArrayList<Player> getMidfielders(){
        return midfielders;
    }
    public ArrayList<Player> getForwards(){
        return forwards;
    }
    public ArrayList<Player> getGoalies(){
        return goalies;
    }
    public void addD(Player myPlayer){
        defenders.add(myPlayer);
    }
    public void addM(Player myPlayer){
        midfielders.add(myPlayer);
    }
    public void addF(Player myPlayer){
        forwards.add(myPlayer);
    }
    public void addG(Player myPlayer){
        goalies.add(myPlayer);
    }

}
