package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;
import static org.junit.Assert.*;

public class SomeTest {

    @Test
    public void wasCorrectlyAnsweredWhenPlayerHasNotSixPursesShouldNotWin() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");

        // When
        boolean notWinner = game.correctAnswer();

        // Then
        assertTrue(notWinner);
    }

    @Test
    public void wasCorrectlyAnsweredWhenPlayerHasSixPursesShouldWin() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.players.get(0).purses = 5;

        // When
        boolean notWinner = game.correctAnswer();

        // Then
        assertFalse(notWinner);
    }

    @Test
    public void bugWasCorrectlyAnsweredShouldGettingOutPenaltyBox() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.players.get(0).inPenaltyBox = true;
        game.isGettingOutOfPenaltyBox = true;

        // When
        game.correctAnswer();

        // Then
        assertFalse(game.players.get(0).inPenaltyBox);
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
        assertEquals(0, game.players.get(0).places);
        assertEquals(0, game.players.get(0).purses);
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
        assertTrue(game.players.get(0).inPenaltyBox);
    }

    @Test
    public void wasCorrectlyAnsweredWhenPlayerNotInPenaltyBox() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");

        // When
        game.correctAnswer();

        // Then
        assertEquals(1, game.players.get(0).purses);
    }

    @Test
    public void wasCorrectlyAnsweredWhenPlayerInPenaltyBoxButNotGettingOut() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.players.get(0).inPenaltyBox = true;

        // When
        game.correctAnswer();

        // Then
        assertEquals(0, game.players.get(0).purses);
    }

    @Test
    public void wasCorrectlyAnsweredWhenPlayerInPenaltyBoxAndGettingOut() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.players.get(0).inPenaltyBox = true;
        game.isGettingOutOfPenaltyBox = true;

        // When
        game.correctAnswer();

        // Then
        assertEquals(1, game.players.get(0).purses);
        assertFalse(game.players.get(0).inPenaltyBox);
    }

    @Test
    public void rollWhenPlayerNotInPenaltyBoxShouldSetPlace() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.players.get(0).inPenaltyBox = false;

        // When
        game.roll(4);

        // Then
        assertEquals(4, game.players.get(0).places);
    }

    @Test
    public void rollWhenPlayerNotInPenaltyBoxAndPlaceMoreThanElevenShouldSetPlaceFromZero() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.players.get(0).inPenaltyBox = false;
        game.players.get(0).places = 9;

        // When
        game.roll(4);

        // Then
        assertEquals(1, game.players.get(0).places);
    }

    @Test
    public void rollWhenPlayerInPenaltyBoxAndRollOddShouldSetOutOfPenaltyBoxToTrueAndSetPlace() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.players.get(0).inPenaltyBox = true;

        // When
        game.roll(3);

        // Then
        assertTrue(game.isGettingOutOfPenaltyBox);
        assertEquals(3, game.players.get(0).places);
    }

    @Test
    public void rollWhenPlayerInPenaltyBoxAndRollOddShouldSetOutOfPenaltyBoxToTrueAndSetPlaceFromZero() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.players.get(0).inPenaltyBox = true;
        game.players.get(0).places = 9;

        // When
        game.roll(5);

        // Then
        assertTrue(game.isGettingOutOfPenaltyBox);
        assertEquals(2, game.players.get(0).places);
    }

    @Test
    public void rollWhenPlayerInPenaltyBoxAndRollEvenShouldSetOutOfPenaltyBoxToFalse() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.players.get(0).inPenaltyBox = true;

        // When
        game.roll(2);

        // Then
        assertFalse(game.isGettingOutOfPenaltyBox);
        assertEquals(0, game.players.get(0).places);
    }

    @Test
    public void rollWhenPlayerInPenaltyBoxAndRollEvenShouldSetOutOfPenaltyBoxToFalsetest() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.players.get(0).places = 5;

        // When
        game.roll(2);

        // Then
        assertEquals(49, game.rockQuestions.size());
    }
}
