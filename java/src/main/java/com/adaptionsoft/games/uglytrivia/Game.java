package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    List<Player> players = new ArrayList();
    Deck deck;

    int currentPlayer = 0;

    private static String GOOD_ANSWER = "Answer was corrent!!!!";
    private static String WRONG_ANSWER = "Question was incorrectly answered";


    public Game() {
        deck = new Deck();
    }

    public void add(String playerName) {
        players.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer).playerName + " is the current player");
        System.out.println("They have rolled a " + roll);

        boolean isNotPenalty = manageInPenaltyBox(roll);

        if (isNotPenalty) {
            players.get(currentPlayer).setNextPlace(roll);
            System.out.println(players.get(currentPlayer).playerName
                    + "'s new location is "
                    + players.get(currentPlayer).places);
            System.out.println("The category is "
                    + deck.askQuestion(players.get(currentPlayer).places));
        }
    }

    private boolean manageInPenaltyBox(int roll) {
        if (players.get(currentPlayer).isInPenaltyBox()) {
            if (roll % 2 != 0) {
                players.get(currentPlayer).canGettingOutOfPenaltyBox();
                System.out.println(players.get(currentPlayer).playerName + " is getting out of the penalty box");
                return true;
            } else {
                System.out.println(players.get(currentPlayer).playerName + " is not getting out of the penalty box");
                players.get(currentPlayer).cannotGettingOutOfPenaltyBox();
                return false;
            }
        }
        return true;
    }

    public boolean correctAnswer() {
        if (players.get(currentPlayer).cannotLeavePenaltyBox()) {
            nextPlayer();
            return true;
        } else {
            return manageCorrectAnswer();
        }
    }

    private boolean manageCorrectAnswer() {
        System.out.println(GOOD_ANSWER);
        players.get(currentPlayer).answerCorrectly();
        System.out.println(players.get(currentPlayer)
                + " now has "
                + players.get(currentPlayer).getCoins()
                + " Gold Coins.");

        boolean winner = players.get(currentPlayer).isWin();
        nextPlayer();
        return !winner;
    }

    public boolean wrongAnswer() {
        System.out.println(WRONG_ANSWER);
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        players.get(currentPlayer).goToPenaltyBox();

        nextPlayer();
        return true;
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }
}
