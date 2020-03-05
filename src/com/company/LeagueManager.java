package com.company;
import static java.lang.System.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class LeagueManager extends User {
//    int privateID;
    String leagueName;
    //    create: ...
    //    close: ...
    //    open: …
    //    setDraftOrder: …
    //    setSchedule: ...
    //    randomSchedule: …
    //    addStatistics: …

    public void leagueConstructor(League newLeague){
        newLeague.name = this.leagueName;
        newLeague.owner = this.name;
    }

    public void close(League myLeague){
        Scanner in = new Scanner(System.in);
        out.println("This is a League Manager command. Please input your 4 digit ID:");
        int givenid = in.nextInt();
        if(givenid == this.privateID) {
            myLeague.open = 0;
            out.println("Your league is now closed.");
        }else{
            out.println("Incorrect ID.");
        }
    }
    public void open(League myLeague){
        Scanner in = new Scanner(System.in);
        out.println("This is a League Manager command. Please input your 4 digit ID:");
        int givenid = in.nextInt();
        if(givenid == this.privateID) {
            myLeague.open = 1;
            out.println("Your league is now open.");
        }else{
            out.println("Incorrect ID.");
        }
    }

    public void addStatistics(League myLeague){
        Scanner in = new Scanner(System.in);
        out.println("This is a League Manager command. Please input your 4 digit ID:");
        int givenid = in.nextInt();
        if(givenid == this.privateID) {
            for (int i = 0; i < myLeague.myPool.defenders.size(); i++) {
                out.println("Please input the # of Fantasy Points that " + myLeague.myPool.defenders.get(i).name +
                        " earned this week:");
                int x = in.nextInt();
                myLeague.myPool.defenders.get(i).currentWeeksScore = x;
                myLeague.myPool.defenders.get(i).pointsRecord.add(x);
            }
            for (int i = 0; i < myLeague.myPool.midfielders.size(); i++) {
                out.println("Please input the # of Fantasy Points that " + myLeague.myPool.midfielders.get(i).name +
                        " earned this week:");
                int x = in.nextInt();
                myLeague.myPool.midfielders.get(i).currentWeeksScore = x;
                myLeague.myPool.midfielders.get(i).pointsRecord.add(x);
            }
            for (int i = 0; i < myLeague.myPool.forwards.size(); i++) {
                out.println("Please input the # of Fantasy Points that " + myLeague.myPool.forwards.get(i).name +
                        " earned this week:");
                int x = in.nextInt();
                myLeague.myPool.forwards.get(i).currentWeeksScore = x;
                myLeague.myPool.forwards.get(i).pointsRecord.add(x);
            }
            for (int i = 0; i < myLeague.myPool.goalies.size(); i++) {
                out.println("Please input the # of Fantasy Points that " + myLeague.myPool.goalies.get(i).name +
                        " earned this week:");
                int x = in.nextInt();
                myLeague.myPool.goalies.get(i).currentWeeksScore = x;
                myLeague.myPool.goalies.get(i).pointsRecord.add(x);
            }
            out.println("Statistics updated.");
        }else{
            out.println("Incorrect ID.");
        }
    }

    public void setDraftOrder(League myLeague){
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> ordering = new ArrayList<Integer>();
        out.println("Please enter the order in which teams should draft players.");
        for(int i = 0; i < myLeague.teams.size(); i++){
            out.println((i+1) + " - " + myLeague.teams.get(i).name);
        }
        for(int i = 0; i < myLeague.teams.size(); i++){
            ordering.add(in.nextInt());
        }
        myLeague.draftOrder = ordering;
        out.println("Order set.");
    }
//
//    public void randomSchedule(){
//
//    }
}
