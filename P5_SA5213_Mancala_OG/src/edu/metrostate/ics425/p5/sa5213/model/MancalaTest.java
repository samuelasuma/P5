package edu.metrostate.ics425.p5.sa5213.model;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics425.p5.sa5213.model.Mancala.player;

class MancalaTest {
	
		Mancala kalah = new Mancala();
		
	@Test
	void testInstantiation() {
		
		assertTrue(kalah instanceof Mancala);
	}

	@Test
	void test() {
		boolean rowOne = kalah.rowOneBlocked();
		boolean rowTwo = kalah.rowOneBlocked();
		int pitIndex = 2;
		kalah.moveStones(pitIndex);
		player curr_player = kalah.getCurrentPlayer(pitIndex);
		int[] pits = kalah.getPits();
		int lastMarbleIndex = 4;
		kalah.getPlayerTurn(curr_player, lastMarbleIndex, pits);
		
		assertEquals(false, rowOne);
		assertEquals(false, rowTwo);
		assertEquals(player.One, curr_player);
		assertEquals(false, kalah.gameOver(pits));
		assertEquals(null, kalah.winner(kalah.gameOver(pits)));
		
	}
	
	@Test
	void captureTests() {
		int[] pits = kalah.getPits();
		Arrays.fill(pits, 0);
		int player_1_pit = 6 ;
		int player_2_pit = 13;
		pits[3] =1;
		pits[9] =5;
		pits[player_1_pit] = 22;
		pits[player_2_pit] = 6;
		boolean rowOne = kalah.rowOneBlocked();
		boolean rowTwo = kalah.rowOneBlocked();
		int pitIndex = 2;
		kalah.moveStones(pitIndex);
		player curr_player = kalah.getCurrentPlayer(pitIndex);
		int newlastMarbleIndex = 3;
		kalah.capture(curr_player, newlastMarbleIndex);
		
		assertEquals(28, pits[player_1_pit] );
		assertEquals(player.One, curr_player);
		assertEquals(false, rowOne );
		assertEquals(false, rowTwo);
	}
	
	@Test
	void endGameTests() {
		int pitIndex = 2;
		kalah.moveStones(pitIndex);
		player curr_player = kalah.getCurrentPlayer(pitIndex);
		int[] pits = kalah.getPits();
		int lastMarbleIndex = 4;
		kalah.getPlayerTurn(curr_player, lastMarbleIndex, pits);
		Arrays.fill(pits, 0);
		int player_1_pit = 6 ;
		int player_2_pit = 13;
		pits[player_1_pit] = 32;
		pits[player_2_pit] = 16;
		
		assertEquals(true, kalah.gameOver(pits));
		assertEquals(player.One, kalah.winner(kalah.gameOver(pits)));
		
	}

}
