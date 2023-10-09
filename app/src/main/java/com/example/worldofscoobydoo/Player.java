package com.example.worldofscoobydoo;

import java.util.ArrayList;

public class Player {
    // Variables of Our Singleton Class
    private String name;
    private int score;
    private ArrayList<String> historyOfNames = new ArrayList<>();
    private ArrayList<Integer> historyOfScores = new ArrayList<>();
    // Creating Unique Instance of a Player
    private volatile static Player player;
    // Instructor for Player
    private Player() {}
    // getInstance() for the Player Class
    public static Player getPlayer() {
        if (player == null) {
            synchronized(Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
    }
    // Making the Getters
    public String getName() {
        return name;
    }
    public double getScore() {
        return score;
    }
    public ArrayList<String> getHistoryOfNames() {
        return historyOfNames;
    }
    public ArrayList<Integer> getHistoryOfScores() {
        return historyOfScores;
    }
    // Making the Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setHistoryOfNames(ArrayList<String> historyOfNames) {
        this.historyOfNames = historyOfNames;
    }
    public void setScores(ArrayList<Integer> historyOfScores) {
        this.historyOfScores = historyOfScores;
    }
}
