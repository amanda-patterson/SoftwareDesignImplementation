package com.company;

import java.util.ArrayList;

public class Player {
    //attributes
    private String name;
    private String premierTeam;
    private String position;
    private String leagueTeam = "";
    private int currentWeeksScore;
    private ArrayList<Integer> pointsRecord = new ArrayList<Integer>();
    private double avgScore = 0;

    //constructor
    Player(String myName, String myTeam, String myPosition){
        name = myName;
        premierTeam = myTeam;
        position = myPosition;
    }

    public String getName(){
        return name;
    }
    public String getPremierTeam(){
        return premierTeam;
    }
    public String getPosition(){
        return position;
    }
    public String getLeagueTeam(){
        return leagueTeam;
    }
    public void setLeagueTeam(String myTeam){
        leagueTeam = myTeam;
    }
    public int getCurrentWeeksScore(){
        return currentWeeksScore;
    }
    public void setCurrentWeeksScore(int x){
        currentWeeksScore = x;
    }
    public ArrayList<Integer> getPointsRecord(){
        return pointsRecord;
    }
    public void addPointsRecord(int x){
        pointsRecord.add(x);
    }

    //returns true if a player isn't on any team
    public boolean isFree() {
        if (leagueTeam.equals("")){
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
