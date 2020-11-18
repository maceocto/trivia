package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

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

        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        boolean isNotPenalty = manageInPenaltyBox(roll);

        if (isNotPenalty) {
            setNextPlace(roll);
            System.out.println(players.get(currentPlayer)
                    + "'s new location is "
                    + places[currentPlayer]);
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }
    }

    private boolean manageInPenaltyBox(int roll) {
        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
                return true;
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return false;
            }
        }
        return true;
    }

    private void setNextPlace(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) {
            places[currentPlayer] = places[currentPlayer] - 12;
        }
    }

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
        int place = places[currentPlayer];

        if (place == 0 || place == 4 || place == 8) {
            return POP;
        } else if (place == 1 || place == 5 || place == 9) {
            return SCIENCE;
        } else if (place == 2 || place == 6 || place == 10) {
            return SPORT;
        } else {
            return ROCK;
        }
    }

    public boolean correctAnswer() {
        if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox) {
            nextPlayer();
            return true;
        } else {
            return manageCorrectAnswer();
        }
    }

    private boolean manageCorrectAnswer() {
        System.out.println(GOOD_ANSWER);
        inPenaltyBox[currentPlayer] = false;
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        nextPlayer();
        return !winner;
    }

    public boolean wrongAnswer() {
        System.out.println(WRONG_ANSWER);
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        nextPlayer();
        return true;
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    private boolean didPlayerWin() {
        return (purses[currentPlayer] == 6);
    }
}
