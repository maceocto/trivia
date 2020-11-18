package com.adaptionsoft.games.uglytrivia;

public class Player {

    String playerName;
    int places;
    int purses;
    boolean inPenaltyBox;

    Player(String playerName) {
        this.playerName = playerName;
        this.places = 0;
        this.purses = 0;
        this.inPenaltyBox = false;
    }

    public void setNextPlace(int roll) {
        places = places + roll;
        if (places > 11) {
            places = places - 12;
        }
    }
}
