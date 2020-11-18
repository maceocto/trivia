package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Deck {

    private static String POP = "Pop";
    private static String SCIENCE = "Science";
    private static String SPORT = "Sports";
    private static String ROCK = "Rock";
    private static String QUESTION = " Question ";

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    Deck() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(POP + QUESTION + +i);
            scienceQuestions.addLast((SCIENCE + QUESTION + i));
            sportsQuestions.addLast((SPORT + QUESTION + i));
            rockQuestions.addLast(ROCK + QUESTION + i);
        }
    }

    public String askQuestion(int place) {
        if (currentCategory(place) == POP)
            System.out.println(popQuestions.removeFirst());
        if (currentCategory(place) == SCIENCE)
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory(place) == SPORT)
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory(place) == ROCK)
            System.out.println(rockQuestions.removeFirst());
        return currentCategory(place);
    }

    public String currentCategory(int places) {
        switch (places) {
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
}
