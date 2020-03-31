package com.company;

import java.util.ArrayList;
import static java.lang.System.*;

public class Team {
    //user who created the team
    private String owner;
    private String name;

    //Arrays of players on team by position
    private ArrayList<Player> defenderArray = new ArrayList<Player>(); //how many of each position I forget?
    private ArrayList<Player> midfielderArray = new ArrayList<Player>();
    private ArrayList<Player> forwardArray = new ArrayList<Player>();
//    private Player[] midfielderArray = new Player[3];
//    private Player[] forwardArray = new Player[3];
    private Player goalie;
    //Recorded wins/loses of the team by week
    private ArrayList<Integer> teamRecord = new ArrayList<Integer>();
    private int totalPoints = 0;

    Team(String myName, String myOwner){
        name = myName;
        owner = myOwner;
    }

    public String getName(){
        return name;
    }
    public String getOwner(){
        return owner;
    }

    //this checks to make sure that there are never too many players of any type on the team
    //if not then it adds them to the roster, otherwise it returns false
    public boolean addPlayer(Player newPlayer){
        newPlayer.setLeagueTeam(this.name);
        if(newPlayer.getPosition().equals(Player.Position.g)){
            if(this.goalie == null){
                this.goalie = newPlayer;
                return true;
            }
        }else if(newPlayer.getPosition().equals(Player.Position.d)){
            if(this.defenderArray.size() < 4){
                this.defenderArray.add(newPlayer);
                return true;
            }
        }else if(newPlayer.getPosition().equals(Player.Position.m)){
            if(this.midfielderArray.size() < 3){
                this.midfielderArray.add(newPlayer);
                return true;
            }
        }else if(newPlayer.getPosition().equals(Player.Position.f)){
            if(this.forwardArray.size() < 3){
                this.forwardArray.add(newPlayer);
                return true;
            }
        }
        return false;
    }

    public void removePlayer(Player player){
        player.setLeagueTeam("");
        defenderArray.remove(player);
        midfielderArray.remove(player);
        forwardArray.remove(player);
        if (goalie.equals(player)){
            goalie = null;
        }
    }

    public void printTeam(){
        out.println(this.name + " owned by: " + this.owner);
        for (Player player: defenderArray){
            player.printPlayer();
        }
        for (Player player: midfielderArray){
            player.printPlayer();
        }
        for (Player player: forwardArray){
            player.printPlayer();
        }
        goalie.printPlayer();
    }

    public int sumPoints(){
        int sum = 0;
        for (Player player: defenderArray){
            sum += player.getCurrentWeeksScore();
        }
        for (Player player: midfielderArray){
            sum += player.getCurrentWeeksScore();
        }
        for (Player player: forwardArray){
            sum += player.getCurrentWeeksScore();
        }
        if(goalie != null) {
            sum += goalie.getCurrentWeeksScore();
        }
        return sum;
    }

    public Player getPlayerFromName(String name){
        if(goalie.getName().equals(name)){
            return goalie;
        }
        for (Player player : defenderArray){
            if(player.getName().equals(name)){
                return player;
            }
        }
        for (Player player : midfielderArray){
            if(player.getName().equals(name)){
                return player;
            }
        }
        for (Player player : forwardArray){
            if(player.getName().equals(name)){
                return player;
            }
        }
        return null;
    }

    //Teams get a 2 for a win, 1 for a tie, and 0 for a loss
    public void addToRecord(int result, int points){
        teamRecord.add(result);
        this.totalPoints += points;
    }
    public int calcWins(){
        int numWins = 0;
        for(int game : teamRecord){
            if(game == 2){
                numWins++;
            }
        }
        return numWins;
    }
    public int getTotalPoints(){
        return totalPoints;
    }


}
