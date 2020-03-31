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
        LeagueManager myManager = new LeagueManager();
        League myLeague = new League();
        ArrayList<TeamManager> myTeamManagers = new ArrayList<TeamManager>();

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
                        if(myLeague.isOpen() == 1) {
                            if (myLeague.getName() != null)
                                out.println("There is already a league set up. Would you like to join " + myLeague.getName() + " as a team manager? y/n");
                            if (in.nextLine().equals("y")) {
                                TeamManager newManager = new TeamManager();
                                newManager.joinLeague(myLeague);
                                myTeamManagers.add(newManager);
//                            for(TeamManager m: myTeamManagers){
//                                out.println(m.getName());
//                            }
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
//                case "addStats":
//                    myManager.addStatistics(myLeague);
//                    break;
                case "setDraftOrder":
                    myManager.setDraftOrder(myLeague);
                    break;
                case "setSchedule":
                    myManager.scheduler(myLeague);
                    break;
                case "startDraft":
                    myManager.startDraft(myTeamManagers, myLeague);
                    break;
                case "startSeason":
                    myManager.startSeason(myLeague);
//                    myLeague.leagueController(myManager);
                    break;
                case "draftPlayer":
                    out.println("Please enter your username:");
                    String username = in.nextLine();
                    TeamManager current = myLeague.getManagerFromName(myTeamManagers, username);
                    out.println("Please enter your team name:");
                    String currentTeam = in.nextLine();
                    current.draftPlayer(currentTeam, myLeague);
                    break;
                case "tradePlayer":
                    out.println("Please enter your username:");
                    String username1 = in.nextLine();
                    TeamManager current1 = myLeague.getManagerFromName(myTeamManagers, username1);
                    current1.tradePlayer(myLeague, myTeamManagers);
                    break;
                case "dropPlayer":
                    out.println("Please enter your username:");
                    String username2 = in.nextLine();
                    TeamManager current2 = myLeague.getManagerFromName(myTeamManagers, username2);
                    out.println("Please enter your team name:");
                    String currentTeam1 = in.nextLine();
                    current2.dropPlayer(currentTeam1, myLeague);
                    break;
                default:
                    out.println("Please provide a valid command:");
            }
        }
    }
}
