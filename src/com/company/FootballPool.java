package com.company;

import java.util.ArrayList;

public class FootballPool {
    private ArrayList<Player> players = new ArrayList<Player>();
//    private ArrayList<Player> defenders = new ArrayList<Player>();
//    private ArrayList<Player> midfielders = new ArrayList<Player>();
//    private ArrayList<Player> forwards = new ArrayList<Player>();
//    private ArrayList<Player> goalies = new ArrayList<Player>();
    //This is just for testing purposes. In Assignment 3 we will have real data loaded automatically or from csv.//
        Player defender1 = new Player("John Smith", "Liverpool", Player.Position.d);
        Player midfielder1 = new Player("James Guy", "Manchester", Player.Position.m);
        Player forward1 = new Player("Foot Baller", "Team", Player.Position.f);
        Player goalie1 = new Player("Andrew Cleats", "AnotherTeam", Player.Position.g);
        Player defender2 = new Player("John Smith2", "Liverpool", Player.Position.d);
        Player midfielder2 = new Player("James Guy2", "Manchester", Player.Position.m);
        Player forward2 = new Player("Foot Baller2", "Team", Player.Position.f);
        Player goalie2 = new Player("Andrew Cleats2", "AnotherTeam", Player.Position.g);
    protected FootballPool() {
        players.add(defender1);
        players.add(midfielder1);
        players.add(forward1);
        players.add(goalie1);
    }
//    public ArrayList<Player> getDefenders(){
//        return defenders;
//    }
//    public ArrayList<Player> getMidfielders(){
//        return midfielders;
//    }
//    public ArrayList<Player> getForwards(){
//        return forwards;
//    }
//    public ArrayList<Player> getGoalies(){
//        return goalies;
//    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
//    public void addD(Player myPlayer){
//        defenders.add(myPlayer);
//    }
//    public void addM(Player myPlayer){
//        midfielders.add(myPlayer);
//    }
//    public void addF(Player myPlayer){
//        forwards.add(myPlayer);
//    }
//    public void addG(Player myPlayer){
//        goalies.add(myPlayer);
//    }
    public void addPlayer(Player myPlayer){
        players.add(myPlayer);
    }

    public void printPlayers(){
        for(Player player: players){
            player.printPlayer();
        }
    }

}
