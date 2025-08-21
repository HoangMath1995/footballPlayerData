package com.example.entity;

public class PlayerInfo {
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerIndexId() {
        return playerIndexId;
    }

    public void setPlayerIndexId(int playerIndexId) {
        this.playerIndexId = playerIndexId;
    }

    private int playerIndexId;
    private String playerName;
    private int playerAge;
    private String indexName;
    private float value;

    // Constructor, Getters and Setters
}


