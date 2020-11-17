package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

public class SomeTest {

	@Test
	public void howManyPlayersShouldReturnNumberOfPlayers() {
		// Given
		Game game = new Game();

		// When
		game.add("Paulo");
		game.add("Mariane");

		// Then
		assertEquals(2, game.howManyPlayers());
	}
}
