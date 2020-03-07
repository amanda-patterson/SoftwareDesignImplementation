package com.company;

import java.util.ArrayList;

import static java.lang.System.*;

public class League {
    private String name;
    private String owner;
    private ArrayList<Team> teams = new ArrayList<Team>();
    private FootballPool myPool = new FootballPool();
    private ArrayList<Integer> draftOrder;
    private Integer[][] scheduledMatches;
    private int open = 1; //1 means teams can be added, 0 means no more teams allowed

    //This is just for testing. Teams will not be added this way in Assignment 3.
    Team team0 = new Team("Jerry");
    Team team1 = new Team("Lakers") ;
    Team team2 = new Team("WinnersOnly");
    Team team3 = new Team("ILoveSoccer");
    Team team4 = new Team("OddTeam!");
    Team team5 = new Team("AnotherTeam!");
    Team team6 = new Team("LastTeam!");
    protected League() {
        teams.add(team0);
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);
//        teams.add(team5);
//        teams.add(team6);
    }
    public void open(){
        open = 1;
    }
    public void close(){
        open = 0;
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
    public ArrayList<Integer> getDraftOrder(){
        return draftOrder;
    }
    public void setDraftOrder(ArrayList<Integer> myOrder){
        draftOrder = myOrder;
    }
    public Integer[][] getScheduledMatches(){
        return scheduledMatches;
    }


    public void setSchedule(){
        ArrayList<Integer> schedule = new ArrayList<Integer>();
        out.println("Weekly Schedule: ");
        int n = teams.size();
        for(int i = 1; i < n; i++){
            schedule.add(i);
        }
        if(n%2 == 0){
            scheduledMatches = new Integer[n][n-1];
            for(int i = 0; i < n-1; i++) {
                out.println("Week " + (i+1) + ": ");
                out.println(teams.get(0).getName() + " vs " + teams.get(schedule.get(0)).getName());
                scheduledMatches[i][0] = 0;
                scheduledMatches[i][1] = schedule.get(0);
                int y = schedule.size() - 1;
                for (int j = 1; j < schedule.size()/2+1; j++){
                    out.println(teams.get(schedule.get(j)).getName() + " vs " + teams.get(schedule.get(y)).getName());
                    int sched = 2;
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
            scheduledMatches = new Integer[n][n];
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
    }

}