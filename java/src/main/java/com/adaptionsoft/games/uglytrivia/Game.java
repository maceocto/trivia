package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    List<Player> players = new ArrayList();

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    private static String POP = "Pop";
    private static String SCIENCE = "Science";
    private static String SPORT = "Sports";
    private static String ROCK = "Rock";
    private static String QUESTION = " Question ";
    private static String GOOD_ANSWER = "Answer was corrent!!!!";
    private static String WRONG_ANSWER = "Question was incorrectly answered";


    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(POP + QUESTION + +i);
            scienceQuestions.addLast((SCIENCE + QUESTION + i));
            sportsQuestions.addLast((SPORT + QUESTION + i));
            rockQuestions.addLast(ROCK + QUESTION + i);
        }
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
            setNextPlace(roll);
            System.out.println(players.get(currentPlayer).playerName
                    + "'s new location is "
                    + players.get(currentPlayer).places);
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }
    }

    private boolean manageInPenaltyBox(int roll) {
        if (players.get(currentPlayer).inPenaltyBox) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(players.get(currentPlayer).playerName + " is getting out of the penalty box");
                return true;
            } else {
                System.out.println(players.get(currentPlayer).playerName + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return false;
            }
        }
        return true;
    }

    private void setNextPlace(int roll) {
        players.get(currentPlayer).places = players.get(currentPlayer).places + roll;
        if (players.get(currentPlayer).places > 11) {
            players.get(currentPlayer).places = players.get(currentPlayer).places - 12;
        }
    }

    //Deck
    private void askQuestion() {
        if (currentCategory() == POP)
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == SCIENCE)
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == SPORT)
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == ROCK)
            System.out.println(rockQuestions.removeFirst());
    }

    private String currentCategory() {
        switch (players.get(currentPlayer).places) {
            case 0:
            case 4:
            case 8:
                return POP;
            case 1:
            case 5:
            case 9:
                return SCIENCE;
            case 2:
            case 6:
            case 10:
                return SPORT;
            default:
                return ROCK;
        }
    }

    public boolean correctAnswer() {
        if (players.get(currentPlayer).inPenaltyBox && !isGettingOutOfPenaltyBox) {
            nextPlayer();
            return true;
        } else {
            return manageCorrectAnswer();
        }
    }

    private boolean manageCorrectAnswer() {
        System.out.println(GOOD_ANSWER);
        players.get(currentPlayer).inPenaltyBox = false;
        players.get(currentPlayer).purses++;
        System.out.println(players.get(currentPlayer)
                + " now has "
                + players.get(currentPlayer).purses
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        nextPlayer();
        return !winner;
    }

    public boolean wrongAnswer() {
        System.out.println(WRONG_ANSWER);
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        players.get(currentPlayer).inPenaltyBox = true;

        nextPlayer();
        return true;
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    private boolean didPlayerWin() {
        return (players.get(currentPlayer).purses == 6);
    }
}
