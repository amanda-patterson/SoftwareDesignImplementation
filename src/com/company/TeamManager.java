package com.company;

import java.util.Scanner;

import static java.lang.System.out;

public class TeamManager extends User {
    Scanner in = new Scanner(System.in);

    public void joinLeague(League myLeague){
        out.println("Congrats! You are a new team manager. "
                + "Let's get some information to get started.");

        out.println("What would you like your username to be?");
        String newName = in.nextLine();
        this.setName(newName);

        out.println("Pick a 4 digit user ID. You will need this to access your team(s)."
                + "\n ID: ");
        int newPin = in.nextInt();
        this.setPrivateID(newPin);

        out.println("You must have at least one team in the league");
        this.createNewTeam(myLeague);

        out.println("You're all set up!");
    }

    public void leaveLeague(League myLeague){
        for(String leaving : this.teams){
            for(Team team : myLeague.getTeams()){
                if(leaving.equals(team.getName())){
                    myLeague.getTeams().remove(team);
                }
            }
        }
    }



}
