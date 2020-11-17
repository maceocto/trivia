package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.junit.Assert.*;

public class SomeTest {

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
    public void wrongAnswer() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");

        // When
        game.wrongAnswer();

        // Then
        assertTrue(game.inPenaltyBox[0]);
        assertEquals(1, game.currentPlayer);
    }

    @Test
    public void wasCorrectlyAnsweredWhenPlayerNotInPenaltyBox() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");

        // When
        boolean winner = game.wasCorrectlyAnswered();

        // Then
        assertEquals(1, game.currentPlayer);
        assertEquals(1, game.purses[0]);
        assertTrue(winner);
    }

    @Test
    public void wasCorrectlyAnsweredWhenPlayerInPenaltyBoxButNotGettingOut() {
        // Given
        Game game = new Game();
        game.add("Paulo");
        game.add("Mariane");
        game.inPenaltyBox[0] = true;

        // When
        boolean winner = game.wasCorrectlyAnswered();

        // Then
        assertEquals(1, game.currentPlayer);
        assertEquals(0, game.purses[0]);
        assertTrue(winner);
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
        boolean winner = game.wasCorrectlyAnswered();

        // Then
        assertEquals(1, game.currentPlayer);
        assertEquals(1, game.purses[0]);
        assertTrue(winner);
    }
}
