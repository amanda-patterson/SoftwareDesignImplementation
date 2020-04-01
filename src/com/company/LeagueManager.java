package com.company;
import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Scanner;

public class LeagueManager extends User {

    private String leagueName;
    public LeagueManager(Subject subject){
        this.subject = subject;
        subject.attach(this);
    }

    public void leagueSetUp(League newLeague){
        newLeague.setName(this.leagueName);
        newLeague.setOwner(this.getName());
    }
    public void setLeagueName(String newName){
        leagueName = newName;
    }
    public void close(League myLeague){
        if(checkID(1)) {
            myLeague.close();
            out.println("Your league is now closed.");
        }else{
            out.println("Incorrect ID.");
        }
    }
    public void open(League myLeague){
        if(checkID(1)) {
            myLeague.open();
            out.println("Your league is now open.");
        }else{
            out.println("Incorrect ID.");
        }
    }

    public void addStatistics(League myLeague){
        if(checkID(1)){
            for (int i = 0; i < myLeague.getPool().getPlayers().size(); i++) {
                out.println("Please input the # of Fantasy Points that " + myLeague.getPool().getPlayers().get(i).getName() +
                        " earned this week:");
                int x = in.nextInt();
                myLeague.getPool().getPlayers().get(i).setCurrentWeeksScore(x);
                myLeague.getPool().getPlayers().get(i).addPointsRecord(x);
                myLeague.getPool().getPlayers().get(i).updateAvgScore();
            }
            out.println("Statistics updated.");
        }else{
            out.println("Incorrect ID.");
        }
    }

    public void setDraftOrder(League myLeague){
        if(checkID(1)) {
            if(myLeague.isOpen() == 0) {
                ArrayList<Integer> ordering = new ArrayList<Integer>();
                out.println("Please enter the order in which teams should draft players.");
                for (int i = 0; i < myLeague.getTeams().size(); i++) {
                    out.println((i + 1) + " - " + myLeague.getTeams().get(i).getName());
                }
                for (int i = 0; i < myLeague.getTeams().size(); i++) {
                    ordering.add(in.nextInt() - 1);
                }
                myLeague.setDraftOrder(ordering);
                out.println("Order set.");
            }else{
                out.println("You must close the league first.");
            }
        }else{
            out.println("Incorrect ID.");
        }
    }
    
    public void scheduler(League myLeague){
        if(checkID(1)) {
            myLeague.setSchedule();
        }else{
            out.println("Incorrect ID.");
        }
    }

    public void startDraft(ArrayList<TeamManager> managers, League myLeague){
        if(checkID(1)) {
            if(myLeague.runDraft(managers)) {
                out.println("The draft is complete.");
            }
        }else{
            out.println("Incorrect ID.");
        }
    }

    public void startSeason(League myLeague, ArrayList<TeamManager> managers) throws InterruptedException {
        if(checkID(1)) {
            myLeague.leagueController(this, managers);
        }else{
            out.println("Incorrect ID.");
        }
    }


}
