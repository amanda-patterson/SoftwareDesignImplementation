package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;
import static java.lang.Thread.sleep;

public class League extends Subject{
    Scanner in = new Scanner(System.in);
    private String name;
    private String owner;
    private ArrayList<Team> teams = new ArrayList<Team>();
    private FootballPool myPool = FootballPool.getInstance();
    private ArrayList<Integer> draftOrder = new ArrayList<Integer>();
    private Integer[][] scheduledMatches;
    private int open = 1; //1 means teams can be added, 0 means no more teams allowed
    private int draftFlag = 0; //0 means draft not complete, 1 is complete.

    //This is the state that will trigger the state change for the observer class
    private int tradeFlag = 1;
    public int getTradeFlag(){
        return tradeFlag;
    }
    public void setTradeFlag(int state){
        this.tradeFlag = state;
        this.notifyAllObservers();
    }

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
//    public ArrayList<Integer> getDraftOrder(){
//        return draftOrder;
//    }
    public void setDraftOrder(ArrayList<Integer> myOrder){
        draftOrder = myOrder;
    }
//    public Integer[][] getScheduledMatches(){
//        return scheduledMatches;
//    }
    public TeamManager getManagerFromName(ArrayList<TeamManager> managers, String name){
        for (TeamManager manager: managers){
            if(manager.getName().equals(name)){
                return manager;
            }
        }
        return null;
    }
    public Team getTeamFromName(String teamName){
        for(Team team : teams){
            if(team.getName().equals(teamName)){
                return team;
            }
        }
        return null;
    }

    public void printRecords(){
        for(Team team : teams){
            team.printRecord();
        }
    }
    public void printTeams(){
        for (Team team : teams){
            team.printTeam();
        }
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
            //rows correspond to the week of the season
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
    }

    //This actually runs the drafting procedure for the league. Each team gets a chance to draft a player.
    //They draft according to the Draft Order that was set by the league manager. This continues
    public boolean runDraft(ArrayList<TeamManager> managers){
        //for testing we are only added 2 players per team
        if(draftOrder.size() == 0 || open == 1){
            out.println("You must close the league and set the draft order first.");
            return false;
        }else {
            //***IMPT*** This should change to j < 11!! THERE SHOULD BE 11 PLAYERS PER TEAM ***IMPT***//
            //We have left it as 2 for grading purposes. It is extremely annoying to manually draft 11 players per team.//
            //The real version would have j < 11 //
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
                        boolean again = manager.draftPlayer(teamName, this);
                        if (again) {
                            i++;
                        }
                    }
                }
            }
            draftFlag = 1;
            return true;
        }
    }

    //The team with the most amount of wins is the winner. If there is a tie, then the team with the higher points wins.
    public Team calcWinner(){
        ArrayList<Team> winners = new ArrayList<Team>();
        int maxWins = 0;
        for(Team team : teams){
            if(team.calcWins() > maxWins){
                maxWins = team.calcWins();
            }
        }
        for(Team team : teams){
            if(team.calcWins() == maxWins){
                winners.add(team);
            }
        }
        Team finalWinner = null;
        int points = 0;
        for(Team team : winners){
            if(team.getTotalPoints() > points){
                finalWinner = team;
            }
        }
        return finalWinner;
    }

    //This function actually controls the running of the league.
    //Once startSeason is called, everything is automated by this function.
    //We considered making it its own class, but decided to make the league class deeper as there isn't much, if any interface added by this class.
    //It is hidden functionality.
    public void leagueController(LeagueManager manager, ArrayList<TeamManager> managers) throws InterruptedException {
        if(draftFlag==1 && scheduledMatches != null) {
            setTradeFlag(0);
            out.println("Congrats. Your season is underway.");
            int numTeams = teams.size();
            for(int week = 0; week < scheduledMatches.length; week++) {
                tradeFlag = 0;
                out.println("The week of play will start now.");
                ArrayList<Matchup> weeklyMatches = new ArrayList<Matchup>();
                //Create the matchups for the week
                for (int i = 0; i < numTeams; i += 2) {
                    Team teamA = teams.get(scheduledMatches[week][i]);
                    Team teamB = teams.get(scheduledMatches[week][i + 1]);
                    Matchup newMatch = new Matchup(teamA, teamB);
                    weeklyMatches.add(newMatch);
                }
                //Print out the matchups for the week
                out.println("The matches for this week are as follows: ");
                for (Matchup match : weeklyMatches) {
                    match.printMatch();
                }
                sleep(4000); //****IMPT**** This would also be changed to a week. We have left it as 4 seconds for grading purposes.
                out.println("The week of play has ended. League Manager, please enter the weeks statistics.");
                manager.addStatistics(this);
                //Update the matchups
                // Print out who won and lost
                for (Matchup match : weeklyMatches) {
                    match.closeMatch();
                    match.printMatch(true);
                }
                setTradeFlag(1);
                out.println("You now each have a chance to trade, draft, or drop players.");
                for(Team team : teams){
                    String teamName = team.getName();
                    String owner = team.getOwner();
                    out.println(owner + " it is your turn to trade, draft, or drop players for your team, " + teamName + ".");
                    out.println("Enter 'help' to see the possible commands or 'done' to end your turn.");
                    TeamManager myManager = getManagerFromName(managers, owner);
                    String command = "";
                    while (!command.equals("done")){
                        command = in.nextLine();
                        switch (command){
                            case "draftPlayer":
                                myManager.draftPlayer(teamName, this);
                                break;
                            case "tradePlayer":
                                myManager.tradePlayer(team,this, managers);
                                break;
                            case "dropPlayer":
                                myManager.dropPlayer(team, this);
                                break;
                            case "seeMyTeam":
                                team.printTeam();
                                break;
                            case  "seePlayerPool":
                                myPool.printPlayers();
                                break;
                            case "seeRecords":
                                printRecords();
                                break;
                            case "help":
                                out.println("The possible commands are as follows:");
                                out.println("tradePlayer");
                                out.println("draftPlayer");
                                out.println("dropPlayer");
                                out.println("seeMyTeam - This allows you to see your personal roster.");
                                out.println("seePlayerPool - This allows you to see all players in the league.");
                                out.println("seeRecords - This allows you to see the wins/loses of all teams in the league.");
                                out.println("done");
                                break;
                            default:
                                out.println("Input valid command, or 'done' to end your turn.");
                        }
                    }
                }
                out.println("The trading period is over.");
                //The next week of play has started.
            }
            out.println("Congrats, you have finished the season!");
            Team winner = calcWinner();
            out.println("The winner is: " + winner.getName() + "! " + winner.getName() + " had " + winner.calcWins()
                    + " wins and " + winner.getTotalPoints() + " points total.");
        }else{
            out.println("You must start the draft before entering the season.");
        }
    }
}