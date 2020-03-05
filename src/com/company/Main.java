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
        newManager.name = newName;

        out.println("Pick a 4 digit user ID. You will need this to access your team/league."
        + "\n ID: ");
        int newPin = in.nextInt();
        newManager.privateID = newPin;

        out.println("What would you like your league name to be? (no spaces please)");
        String newLeagueName = in2.nextLine();
        newManager.leagueName = newLeagueName;

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
                            out.println("Congratulations. Your league, " + myLeague.name + ", now exists. What would you like to do next?");
                        }else{
                            out.println("Okay. Goodbye.");
                        }
                    }else{
                        if(myLeague.name != null) out.println("There is already a league set up. Would you like to join " + myLeague.name + " as a team manager? y/n");
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
                default:
                    out.println("Please provide a valid command:");
            }
        }



//        out.println("Hello! Welcome to Fantasy Soccer. " +
//                "Are you a new or returning user?" + "\n Type 1 for 'new' or 2 for 'returning':");
//        int choice1 = in.nextInt();
//        if (choice1 == 1) {
//            out.println("New User! " + "Would you like to join a league or create your own?" +
//                    "\n Type 1 for 'join' or 2 for 'create':");
//            int choice2 = in.nextInt();
//            if (choice2 == 2) {
//                managers.add(managerConstructor());
//                leagues.add(managers.get(managers.size()-1).leagueConstructor());
//            }
//        }else if(choice1 == 2){
//            out.println("Returning User! " + "Are you a League Manager? y/n");
//            String choice3 = in2.nextLine();
//            if(choice3.equals("y")){
//                out.println("Input your Name and User ID.");
//                out.println("Name:");
//                String currentName = in2.nextLine();
//                out.println("ID:");
//                int currentID = in2.nextInt();
//
//                int currentManagerNum;
//                for (int i = 0; i < managers.size(); i++) {
//                    if(managers.get(i).name.equals(currentName) && (managers.get(i).privateID == currentID)) {
//                        currentManagerNum = i;
//                        out.println("Welcome " + managers.get(currentManagerNum).name);
//                    }else{
//                        out.println("Sorry! We don't seem to recognize you.");
//                    }
//                }
//            }else if(choice3 == "n"){
//                //Implement in Assignment 3
//                out.println("Sorry, TeamManager functionality is coming soon!");
//            }else{
//                //fix this here so they can input again
//                out.println("Invalid input");
//            }
//        }
//        for (int i = 0; i < leagues.size(); i++) {
//            out.println(leagues.get(i).name);
//        }
    }
}
