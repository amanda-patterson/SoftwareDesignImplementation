package com.company;

import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.System.*;

public class Main {
    public static void managerSetUp(LeagueManager newManager){
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in); //I could not for the life of me figure out why I need 2 scanners, but it works with 2 and not with 1, so here we are

        out.println("Congrats! You are creating your own league. "
        + "Let's get some information to get started.");

        out.println("What would you like your username to be?");
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

    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        String command = "";
        int leagueFlag = 0;
        League myLeague = new League();
        LeagueManager myManager = new LeagueManager(myLeague);
        ArrayList<TeamManager> myTeamManagers = new ArrayList<TeamManager>();

        out.println("Hello and welcome to Fantasy Soccer! To begin type 'start': ");
        while (!command.equals("exit-program")){
            command = in.nextLine();
            switch(command){
                case "start":
                    if (leagueFlag == 0){
                        out.println("No league has been set up yet. Would you like to create one and become the League Manager? y/n");
                        if(in.nextLine().equals("y")){
                            managerSetUp(myManager);
                            myManager.leagueSetUp(myLeague);
                            leagueFlag = 1;
                            out.println("Congratulations. Your league, " + myLeague.getName() + ", now exists. What would you like to do next?");
                        }else{
                            out.println("Okay. Goodbye.");
                        }
                    }else{
                        if(myLeague.isOpen() == 1) {
                            if (myLeague.getName() != null)
                                out.println("There is already a league set up. Would you like to join " + myLeague.getName() + " as a team manager? y/n");
                            if (in.nextLine().equals("y")) {
                                TeamManager newManager = new TeamManager(myLeague);
                                newManager.joinLeague(myLeague);
                                myTeamManagers.add(newManager);
                            } else {
                                out.println("Okay. Goodbye.");
                            }
                        }else{
                            out.println("Sorry this league is closed. No more users can join.");
                        }
                    }
                    break;
                case "closeLeague":
                    myManager.close(myLeague);
                    break;
                case "openLeague":
                    myManager.open(myLeague);
                    break;
                case "setDraftOrder":
                    myManager.setDraftOrder(myLeague);
                    break;
                case "initiateSchedule":
                    myManager.scheduler(myLeague);
                    break;
                case "startDraft":
                    myManager.startDraft(myTeamManagers, myLeague);
                    break;
                case "startSeason":
                    myManager.startSeason(myLeague, myTeamManagers);
                    break;
                default:
                    out.println("Please provide a valid command:");
            }
        }
    }
}
