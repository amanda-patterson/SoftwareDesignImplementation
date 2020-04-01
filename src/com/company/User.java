package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public abstract class User extends Observer {
    Scanner in = new Scanner(System.in);
    Scanner in2 = new Scanner(System.in); //I could not for the life of me figure out why I need 2 scanners, but it works with 2 and not with 1, so here we are

    protected String name;
    protected int privateID;
    protected ArrayList<String> teams = new ArrayList<String>();
    protected boolean canTrade = true;

//    public User(Subject subject){
//        this.subject = subject;
//        subject.attach(this);
//    }
    public void update(){
        if(this.subject.getTradeFlag() == 0){
            canTrade = false;
        }else{
            canTrade = true;
        }
    }

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
        if(canTrade) {
            if (checkID()) {
                Team currentTeam = myLeague.getTeamFromName(teamName);
                out.println("Enter the name of the player you want to draft: ");
                String playerName = in2.nextLine();
                for (Player player : myLeague.getPool().getPlayers()) {
                    if (player.getName().equals(playerName)) {
                        if (player.isFree()) {
                            currentTeam.addPlayer(player);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void dropPlayer(Team currentTeam, League myLeague){
        if(canTrade) {
            if (checkID()) {
//                Team currentTeam = myLeague.getTeamFromName(teamName);
                out.println("Enter the name of the player you want to drop");
                String playerName = in.nextLine();
                Player remove = currentTeam.getPlayerFromName(playerName);
                currentTeam.removePlayer(remove);
            }
        }else{
            out.println("This command is not available currently.");
        }
    }
    //Team1 wants to trade player1 for player2 on Team2
    public void tradePlayer(Team teamA, League myLeague, ArrayList<TeamManager> managers){
        if(canTrade) {
            if (checkID()) {
//                out.println("Please input your team name: ");
//                String team1 = in.nextLine();
                out.println("Please input the player you would like to trade away: ");
                String player1 = in2.nextLine();
                out.println("Please input the name of the team you would like to trade with: ");
                String team2 = in2.nextLine();
                out.println("Please input the player you would like to acquire: ");
                String player2 = in2.nextLine();
//                Team teamA = myLeague.getTeamFromName(team1);
                Team teamB = myLeague.getTeamFromName(team2);
                String acceptorName = teamB.getOwner();
                TeamManager acceptor = myLeague.getManagerFromName(managers, acceptorName);
                out.println("Do you accept this trade, " + acceptorName + "? Please input your ID to accept or 0 to decline.");
                if (acceptor.checkID()) {
                    Player playerA = teamA.getPlayerFromName(player1);
                    Player playerB = teamB.getPlayerFromName(player2);
                    teamA.removePlayer(playerA);
                    teamB.removePlayer(playerB);
                    teamA.addPlayer(playerB);
                    teamB.addPlayer(playerA);
                } else {
                    out.println("This trade was declined.");
                }
            }
        }else{
            out.println("This command is not available currently.");
        }
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
