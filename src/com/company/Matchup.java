package com.company;

public class Matchup {
    private Team team1;
    private Team team2;
    private int team1Points;
    private int team2Points;
    private int gameDay;
    private Team winner;

    Matchup(Team teamA, Team teamB){
        team1 = teamA;
        team2 = teamB;
    }

    public void printMatch(){
        System.out.println(team1.getName() + " vs. " + team2.getName());
    }
    public void printMatch(boolean weekOver){
        if(winner != null) {
            System.out.println(team1.getName() + " vs. " + team2.getName() + " - Winner: " + winner.getName());
        }else{
            System.out.println(team1.getName() + " vs. " + team2.getName() + " - Tie Game");
        }
    }

    public void closeMatch(){
        team1Points = team1.sumPoints();
        team2Points = team2.sumPoints();
        if(team1Points > team2Points){
            setWinner(team1, team1Points);
            setLoser(team2, team2Points);
        }else if(team2Points > team1Points){
            setWinner(team2, team2Points);
            setLoser(team1, team1Points);
        }else{
            setTie(team1, team1Points, team2, team2Points);
        }
    }
    public void setWinner(Team winner, int points){
        winner.addToRecord(2, points);
        this.winner = winner;
    }
    public void setLoser(Team loser, int points){
        loser.addToRecord(0, points);
    }
    public void setTie(Team team1, int points1, Team team2, int points2){
        team1.addToRecord(1, points1);
        team2.addToRecord(1, points2);
        this.winner = null;
    }

    public Team getWinner(){
        return this.winner;
    }

}

