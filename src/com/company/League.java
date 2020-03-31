package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

import static java.lang.System.*;
import static java.lang.Thread.sleep;

public class League {
    Scanner in = new Scanner(System.in);
    private String name;
    private String owner;
    private ArrayList<Team> teams = new ArrayList<Team>();
    private FootballPool myPool = new FootballPool();
    private ArrayList<Integer> draftOrder;
    private Integer[][] scheduledMatches;
    private int open = 1; //1 means teams can be added, 0 means no more teams allowed
    private int draftFlag = 1; //0 means draft not complete, 1 is complete.

    //This is just for testing. Teams will not be added this way in Assignment 3.
//    Team team0 = new Team("Jerry");
//    Team team1 = new Team("Lakers") ;
//    Team team2 = new Team("WinnersOnly");
//    Team team3 = new Team("ILoveSoccer");
//    Team team4 = new Team("OddTeam!");
//    Team team5 = new Team("AnotherTeam!");
//    Team team6 = new Team("LastTeam!");
//    protected League() {
//        teams.add(team0);
//        teams.add(team1);
//        teams.add(team2);
//        teams.add(team3);
//        teams.add(team4);
//        teams.add(team5);
//        teams.add(team6);
//    }
    public void open(){
        open = 1;
    }
    public void close(){
        open = 0;
    }
    public int isOpen(){
        return open;
    }
    public String getName(){
        return name;
    }
    public void setName(String myName){
        name = myName;
    }
    public String getOwner(){
        return owner;
    }
    public void setOwner(String myOwner){
        owner = myOwner;
    }
    public FootballPool getPool(){
        return myPool;
    }
    public ArrayList<Team> getTeams(){
        return teams;
    }
    public void addTeam(Team newTeam){
        teams.add(newTeam);
    }
    public ArrayList<Integer> getDraftOrder(){
        return draftOrder;
    }
    public void setDraftOrder(ArrayList<Integer> myOrder){
        draftOrder = myOrder;
    }
    public Integer[][] getScheduledMatches(){
        return scheduledMatches;
    }


    //This is a round robin scheduling algorithm. It ensures that each team plays every other team exactly once.
    //It stores the created schedule in 'scheduledMatches' which is a 2 dimensional array. The rows correspond to the
    //week of the season, and each row contains all the teams that are playing that week. The teams with indices 0 and 1
    //will play eachother, and 2 and 3, and so on.
    public void setSchedule(){
        ArrayList<Integer> schedule = new ArrayList<Integer>();
        out.println("Weekly Schedule: ");
        int n = teams.size();
        for(int i = 1; i < n; i++){
            schedule.add(i);
        }
        if(n%2 == 0){
            //rows correspond to which week of the season
            scheduledMatches = new Integer[n-1][n];
            for(int i = 0; i < n-1; i++) {
                out.println("Week " + (i+1) + ": ");
                out.println(teams.get(0).getName() + " vs " + teams.get(schedule.get(0)).getName());
                scheduledMatches[i][0] = 0;
                scheduledMatches[i][1] = schedule.get(0);
                int y = schedule.size() - 1;
                int sched = 2;
                for (int j = 1; j < schedule.size()/2+1; j++){
                    out.println(teams.get(schedule.get(j)).getName() + " vs " + teams.get(schedule.get(y)).getName());
                    scheduledMatches[i][sched] = schedule.get(j);
                    scheduledMatches[i][sched+1] = schedule.get(y);
                    sched+=2;
                    y--;
                }
                schedule.add(0, schedule.get(n - 2));
                schedule.remove(n-1);
            }
        }else{
            int s = schedule.size()+1;
            schedule.add(s);
            scheduledMatches = new Integer[n][n-1];
            for(int i = 0; i < n; i++) {
                int sched = 0;
                out.println("Week " + (i + 1) + ": ");
                if (schedule.get(0) == s) {
                    out.println("Bye: " + teams.get(0).getName());
                }else{
                    out.println(teams.get(0).getName() + " vs " + teams.get(schedule.get(0)).getName());
                    scheduledMatches[i][sched] = 0;
                    scheduledMatches[i][sched+1] = schedule.get(0);
                    sched+=2;
                }
                int y = schedule.size() - 1;
                for (int j = 1; j < schedule.size()/2+1; j++){
                    if((schedule.get(y) == s) ||  (schedule.get(j) == s)) {
                        if(schedule.get(j) != s) {
                            out.println("Bye: " + teams.get(schedule.get(j)).getName());
                        }else{
                            out.println("Bye: " + teams.get(schedule.get(y)).getName());
                        }
                    }else{
                        out.println(teams.get(schedule.get(j)).getName() + " vs " + teams.get(schedule.get(y)).getName());
                        scheduledMatches[i][sched] = schedule.get(j);
                        scheduledMatches[i][sched+1] = schedule.get(y);
                        sched+=2;
                    }
                    y--;
                }
                schedule.add(0, schedule.get(s - 1));
                schedule.remove(s-1+1 );
            }
        }
        out.println("Schedule set.");
//        Printing method for debugging purposes:
//        for (int i = 0; i < n-1; i++)
//            for (int j = 0; j < n; j++)
//                System.out.println("arr[" + i + "][" + j + "] = "
//                        + scheduledMatches[i][j]);
    }

    public Team getTeamFromName(String teamName){
        for(Team team : teams){
            if(team.getName().equals(teamName)){
                return team;
            }
        }
        return null;
    }

    public void printTeams(){
        for (Team team : teams){
            team.printTeam();
        }
    }

    public boolean runDraft(ArrayList<TeamManager> managers){
        //for testing we are only adding 2 players per team
        //remember to change this
        if(draftOrder.size() == 0 && open == 0){
            out.println("You must close the league and set the draft order first.");
            return false;
        }else {
            for (int j = 0; j < 2; j++) {
                for (int index = 0; index < draftOrder.size(); index++) {
                    int current = draftOrder.get(index);
                    String teamName = teams.get(current).getName();
                    String owner = teams.get(current).getOwner();
                    out.println(owner + " it is your turn to draft for your team, " + teamName + ".");
                    out.println("You will draft 1 player each round of the draft.");
                    out.println("Would you like to see the pool of players? y/n");
                    String response = in.nextLine();
                    if (response.equals("y")) {
                        myPool.printPlayers();
                    }
                    TeamManager manager = getManagerFromName(managers, owner);
                    int i = 0;
                    while (i < 1) {
                        if (manager.draftPlayer(teamName, this)) {
                            i++;
                        }
                    }
                }
            }
            draftFlag = 1;
            return true;
        }
    }
    public TeamManager getManagerFromName(ArrayList<TeamManager> managers, String name){
        for (TeamManager manager: managers){
            if(manager.getName().equals(name)){
                return manager;
            }
        }
        return null;
    }

    //remember to change the flag back @ me!
    public void leagueController(LeagueManager manager) throws InterruptedException {
        if(draftFlag==1) {
            out.println("Congrats. Your season is underway.");
            //Create the matchups for the week
            //Print out the matchups for the week
            sleep(2000); //wait a week
            out.println("The week of play has ended. League Manager, please enter the weeks statistics.");
            manager.addStatistics(this);
            //Update the matchups
            //Update team records
            //Print out who won and lost?
            //"You may now trade, draft, or drop players" -turn on a trade flag
            //Wait 24 hours - turn off trade flag
            //Remove matchups
            //The next week of play has started.

//            Date current = new Date();
//            out.println("here: " + current);
        }else{
            out.println("You must start the draft before entering the season.");
        }
    }


}