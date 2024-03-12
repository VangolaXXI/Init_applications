package com.example.init.adapter;
public class LeaderboardItem {
    private int number;
    private String username;
    private String group;

    public LeaderboardItem(int number, String username, String pin) {
        this.number = number;
        this.username = username;
        this.group = group;
    }

    public int getNumber() {
        return number;
    }

    public String getUsername() {
        return username;
    }

    public String getPin() {
        return group;
    }
}

