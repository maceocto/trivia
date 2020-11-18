package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.junit.Assert.*;

public class SomeTest {

    @Test
    public void bugWasCorrectlyAnsweredWhenPlayerHasNotSixPursesShouldNotWin() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");

        // When
        boolean winner = game.correctAnswer();

        // Then
        assertFalse(winner);
    }

    @Test
    public void bugWasCorrectlyAnsweredWhenPlayerHasSixPursesShouldWin() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.purses[0] = 5;

        // When
        boolean winner = game.correctAnswer();

        // Then
        assertTrue(winner);
    }

    @Test
    public void bugWasCorrectlyAnsweredShouldGettingOutPenaltyBox() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.inPenaltyBox[0] = true;
        game.isGettingOutOfPenaltyBox = true;

        // When
        boolean winner = game.correctAnswer();

        // Then
        assertFalse(game.inPenaltyBox[0]);
    }

    @Test
    public void addShouldAddPlayersToGame() {
        // Given
        Game game = new Game();

        // When
        game.add("Paulo");
        game.add("Mariane");

        // Then
        assertEquals(2, game.players.size());
        assertEquals(0, game.places[0]);
        assertEquals(0, game.purses[0]);
    }

    @Test
    public void wrongAnswerShouldGoToTheNextPlayer() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");

        // When
        game.wrongAnswer();

        // Then
        assertEquals(1, game.currentPlayer);
    }

    @Test
    public void correctlyAnsweredWhenLastPlayerPlayedShouldGoToTheFirstPlayer() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");

        // When
        game.currentPlayer = 1;
        game.correctAnswer();

        // Then
        assertEquals(0, game.currentPlayer);
    }

    @Test
    public void wrongAnswer() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");

        // When
        game.wrongAnswer();

        // Then
        assertTrue(game.inPenaltyBox[0]);
    }

    @Test
    public void wasCorrectlyAnsweredWhenPlayerNotInPenaltyBox() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");

        // When
        boolean winner = game.correctAnswer();

        // Then
        assertEquals(1, game.purses[0]);
    }

    @Test
    public void wasCorrectlyAnsweredWhenPlayerInPenaltyBoxButNotGettingOut() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.inPenaltyBox[0] = true;

        // When
        boolean winner = game.correctAnswer();

        // Then
        assertEquals(0, game.purses[0]);
    }

    @Test
    public void wasCorrectlyAnsweredWhenPlayerInPenaltyBoxAndGettingOut() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.inPenaltyBox[0] = true;
        game.isGettingOutOfPenaltyBox = true;

        // When
        boolean winner = game.correctAnswer();

        // Then
        assertEquals(1, game.purses[0]);
        assertTrue(game.inPenaltyBox[0]);
    }

    @Test
    public void rollWhenPlayerNotInPenaltyBoxShouldSetPlace() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.inPenaltyBox[0] = false;

        // When
        game.roll(4);

        // Then
        assertEquals(4, game.places[0]);
    }

    @Test
    public void rollWhenPlayerNotInPenaltyBoxAndPlaceMoreThanElevenShouldSetPlaceFromZero() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.inPenaltyBox[0] = false;
        game.places[0] = 9;

        // When
        game.roll(4);

        // Then
        assertEquals(1, game.places[0]);
    }

    @Test
    public void rollWhenPlayerInPenaltyBoxAndRollOddShouldSetOutOfPenaltyBoxToTrueAndSetPlace() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.inPenaltyBox[0] = true;

        // When
        game.roll(3);

        // Then
        assertTrue(game.isGettingOutOfPenaltyBox);
        assertEquals(3, game.places[0]);
    }

    @Test
    public void rollWhenPlayerInPenaltyBoxAndRollOddShouldSetOutOfPenaltyBoxToTrueAndSetPlaceFromZero() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.inPenaltyBox[0] = true;
        game.places[0] = 9;

        // When
        game.roll(5);

        // Then
        assertTrue(game.isGettingOutOfPenaltyBox);
        assertEquals(2, game.places[0]);
    }

    @Test
    public void rollWhenPlayerInPenaltyBoxAndRollEvenShouldSetOutOfPenaltyBoxToFalse() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.inPenaltyBox[0] = true;

        // When
        game.roll(2);

        // Then
        assertFalse(game.isGettingOutOfPenaltyBox);
        assertEquals(0, game.places[0]);
    }

    @Test
    public void rollWhenPlayerInPenaltyBoxAndRollEvenShouldSetOutOfPenaltyBoxToFalsetest() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.places[0] = 5;

        // When
        game.roll(2);

        // Then
        assertEquals(49, game.rockQuestions.size());
    }
}
