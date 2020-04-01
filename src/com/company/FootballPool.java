package com.company;

import java.util.ArrayList;

public class FootballPool {
    private ArrayList<Player> players = new ArrayList<Player>();
    //This is just for testing purposes. Were we to roll out this system we would add a loading system for csv files
    //However this was low on our priority list as it was very cumbersome for testing and did not add to the real
    //functionality of our system.
        Player defender1 = new Player("John Smith", "Liverpool", Player.Position.d);
        Player midfielder1 = new Player("James Guy", "Manchester", Player.Position.m);
        Player forward1 = new Player("Foot Baller", "Team", Player.Position.f);
        Player goalie1 = new Player("Andrew Cleats", "AnotherTeam", Player.Position.g);
        Player defender2 = new Player("John Smith2", "Liverpool", Player.Position.d);
        Player midfielder2 = new Player("James Guy2", "Manchester", Player.Position.m);
        Player forward2 = new Player("Foot Baller2", "Team", Player.Position.f);
        Player goalie2 = new Player("Andrew Cleats2", "AnotherTeam", Player.Position.g);

    //We decided to implement the Singleton design pattern for FootballPool
    private static FootballPool firstInstance = null;
    private FootballPool() {
        players.add(defender1);
        players.add(midfielder1);
        players.add(forward1);
        players.add(goalie1);
    }

    public static FootballPool getInstance(){
        if(firstInstance == null){
            firstInstance = new FootballPool();
        }
        return firstInstance;
    }

    //Methods
    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void addPlayer(Player myPlayer){
        players.add(myPlayer);
    }

    public void printPlayers(){
        for(Player player: players){
            player.printPlayer();
        }
    }
}
