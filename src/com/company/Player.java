package com.company;

import java.util.ArrayList;

public class Player {
    //attributes
    String name;
    String premierTeam;
    String position;
    String leagueTeam = "";
    int currentWeeksScore;
    ArrayList<Integer> pointsRecord = new ArrayList<Integer>();
    double avgScore = 0;

    //constructor
    Player(String myName, String myTeam, String myPosition){
        name = myName;
        premierTeam = myTeam;
        position = myPosition;
    }

    //operations
    //returns true if a player isn't on any team
    public boolean isFree() {
        if (leagueTeam == ""){
            return true;
        } else {
            return false;
        }
    }
    public void updateAvgScore(){
        int x = pointsRecord.size();
        int total = 0;
        for (int i = 0; i < x; i++) {
            total += pointsRecord.get(i);
        }
        avgScore = (total*1.0)/x;
    }
}
