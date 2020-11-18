package com.adaptionsoft.games.uglytrivia;

public class Player {

    String playerName;
    int places;
    private int coins;
    private boolean inPenaltyBox;
    private boolean isGettingOutOfPenaltyBox;

    Player(String playerName) {
        this.playerName = playerName;
        this.places = 0;
        this.coins = 0;
        this.inPenaltyBox = false;
        isGettingOutOfPenaltyBox = false;
    }

    public void setNextPlace(int roll) {
        places = places + roll;
        if (places > 11) {
            places = places - 12;
        }
    }

    public void goToPenaltyBox() {
        inPenaltyBox = true;
    }

    public void answerCorrectly() {
        inPenaltyBox = false;
        coins++;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void canGettingOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = true;
    }

    public void cannotGettingOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = false;
    }

    public boolean cannotLeavePenaltyBox() {
        return isInPenaltyBox() && !isGettingOutOfPenaltyBox;
    }

    public int getCoins() {
        return coins;
    }

    public boolean isWin() {
        return coins == 6;
    }
}
