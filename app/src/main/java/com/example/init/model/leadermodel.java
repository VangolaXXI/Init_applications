package com.example.init.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lead_table")
public class leadermodel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String username;
    @ColumnInfo
    private String group;

    public leadermodel() {
        this.id = 0;
        this.username = "";
        this.group = "";
    }

    // Геттеры и сеттеры для каждого поля, если необходимо
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
