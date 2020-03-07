package com.company;

import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.System.*;

public class Main {
    public static void managerConstructor(LeagueManager newManager){
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);

        out.println("Congrats! You are creating your own league. "
        + "Let's get some information to get started.");

        out.println("What's would you like your username to be?");
        String newName = in.nextLine();
        newManager.setName(newName);

        out.println("Pick a 4 digit user ID. You will need this to access your team/league."
        + "\n ID: ");
        int newPin = in.nextInt();
        newManager.setPrivateID(newPin);

        out.println("What would you like your league name to be? (no spaces please)");
        String newLeagueName = in2.nextLine();
        newManager.setLeagueName(newLeagueName);

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command = "";
        int leagueFlag = 0;
        LeagueManager myManager = new LeagueManager();
        League myLeague = new League();

        out.println("Hello and welcome to Fantasy Soccer! To begin type 'start': ");
        while (!command.equals("exit-program")){
            command = in.nextLine();
            switch(command){
                case "start":
                    if (leagueFlag == 0){
                        out.println("No league has been set up yet. Would you like to create one and become the League Manager? y/n");
                        if(in.nextLine().equals("y")){
                            managerConstructor(myManager);
                            myManager.leagueConstructor(myLeague);
                            leagueFlag = 1;
                            out.println("Congratulations. Your league, " + myLeague.getName() + ", now exists. What would you like to do next?");
                        }else{
                            out.println("Okay. Goodbye.");
                        }
                    }else{
                        if(myLeague.getName() != null) out.println("There is already a league set up. Would you like to join " + myLeague.getName() + " as a team manager? y/n");
                        if(in.nextLine().equals("y")){
                            out.println("Sorry, this functionality isn't up and running yet!");
                        }else{
                            out.println("Okay. Goodbye.");
                        }
                    }
                    break;
                case "closeLeague":
                    myManager.close(myLeague);
                    break;
                case "openLeague":
                    myManager.open(myLeague);
                    break;
                case "addStats":
                    myManager.addStatistics(myLeague);
                    break;
                case "setDraftOrder":
                    myManager.setDraftOrder(myLeague);
                    break;
                case "setSchedule":
                    myManager.scheduler(myLeague);
                    break;
                default:
                    out.println("Please provide a valid command:");
            }
        }
    }
}
