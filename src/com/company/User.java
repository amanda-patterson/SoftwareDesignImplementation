package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public abstract class User {
    Scanner in = new Scanner(System.in);

    protected String name;
    protected int privateID;
    protected ArrayList<String> teams = new ArrayList<String>();

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
    public void createNewTeam(League myLeague){
        if(myLeague.isOpen() == 1) {
            out.println("What would you like your team name to be?");
            String teamName = in.nextLine();
            if (myLeague.getTeamFromName(teamName) == null) {
                this.teams.add(teamName);
                Team team = new Team(teamName, this.name);
                myLeague.addTeam(team);
            } else {
                out.println("Name already taken. Try again.");
            }
        }else{
            out.println("This league is closed.");
        }
    }
    public boolean draftPlayer(String teamName, League myLeague){
        Team currentTeam = myLeague.getTeamFromName(teamName);
        out.println("Enter the name of the player you want to draft: ");
        String playerName = in.nextLine();
        for (Player player : myLeague.getPool().getPlayers()){
            if (player.getName().equals(playerName)){
                if(player.isFree()) {
                    currentTeam.addPlayer(player);
                    return true;
                }
            }
        }
        return false;
    }

    public void dropPlayer(String teamName, League myLeague){
        Team currentTeam = myLeague.getTeamFromName(teamName);
        out.println("Enter the name of the player you want to drop");
        String playerName = in.nextLine();
        Player remove = currentTeam.getPlayerFromName(playerName);
        currentTeam.removePlayer(remove);
    }
    //Team1 wants to trade player1 for player2 on Team2
    public void tradePlayer(String team1, String player1, String team2, String player2, League myLeague){
        Team teamA = myLeague.getTeamFromName(team1);
        Team teamB = myLeague.getTeamFromName(team2);
        Player playerA = teamA.getPlayerFromName(player1);
        Player playerB = teamB.getPlayerFromName(player2);
        teamA.removePlayer(playerA);
        teamB.removePlayer(playerB);
        teamA.addPlayer(playerB);
        teamB.addPlayer(playerA);
    }

    public boolean checkID(){
        out.println("Please input your 4 digit ID: ");
        int id = in.nextInt();
        if(id == this.privateID){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkID(int managerFlag){
        out.println("This is a League Manager command. Please input your 4 digit ID: ");
        int id = in.nextInt();
        if(id == this.privateID){
            return true;
        }else{
            return false;
        }
    }

}
