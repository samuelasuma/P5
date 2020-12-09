package edu.metrostate.ics425.p5.sa5213.model;

import java.io.Serializable;
import java.util.Arrays;

public class Mancala implements Serializable {

	public Mancala() {
		super();
		initialize();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	enum player {
		One, Two
	}

	private static final int PLAYER_TWO_BANK = 13;
	private static final int PLAYER_ONE_BANK = 6;
	private boolean rowOneChecker;
	private boolean rowTwoChecker;

	private int pits[];

	public int[] getPits() {

		return this.pits;
	}
	
	public void initialize() {
		pits = new int[14];
		Arrays.fill(pits, 4);
		pits[6] = 0;
		pits[13] = 0;

	}

	public void moveStones(int pitIndex) {
		int lastMarbleIndex = 0;
		int marbles = this.pits[pitIndex];
		this.pits[pitIndex] = 0;
		player currentPlayer = getCurrentPlayer(pitIndex);

		for (int i = 0; i < marbles; i++) {
			lastMarbleIndex = pitIndex = (pitIndex + 1) % 14;
			if (currentPlayer == player.One && pitIndex == PLAYER_TWO_BANK) {
				pitIndex = 0;
			} else if (currentPlayer == player.Two && pitIndex == PLAYER_ONE_BANK) {
				pitIndex = 7;
			}
			this.pits[pitIndex]++;
		}
		
		getPlayerTurn(currentPlayer, lastMarbleIndex, pits);
		capture(currentPlayer, lastMarbleIndex);
		
		
	}

	public player getCurrentPlayer(int index) {

		if (index >= 0 && index <= 5) {
			return player.One;
		} else {
			return player.Two;
		}
	}
	
	public void capture(player currentplayer, int lastMarbleIndex  ) {
		if (currentplayer == player.One && pits[lastMarbleIndex] == 1  && 0 <= lastMarbleIndex && lastMarbleIndex <  PLAYER_ONE_BANK ) {
			int oppositeIndex = (- lastMarbleIndex )+ 12;
            pits[PLAYER_ONE_BANK] += (pits[oppositeIndex]) + 1;
            pits[lastMarbleIndex]=0;
            pits[oppositeIndex]=0;
		}else if (currentplayer == player.Two && pits[lastMarbleIndex] == 1  && PLAYER_ONE_BANK + 1 <= lastMarbleIndex && lastMarbleIndex <  PLAYER_TWO_BANK){
			int oppositeIndex = (12 - lastMarbleIndex );
			pits[PLAYER_TWO_BANK] +=  (pits[oppositeIndex]) + 1;
            pits[lastMarbleIndex]=0;
            pits[oppositeIndex]=0;
		}
		 	
	}

	public void getPlayerTurn(player currentplayer, int lastMarbleIndex, int[] pits) {
		if (currentplayer == player.One && pits[lastMarbleIndex] >= 1) {
			this.rowOneChecker = true;
			this.rowTwoChecker = false;
		} else if (currentplayer == player.Two && pits[lastMarbleIndex] >= 1) {
			this.rowTwoChecker = true;
			this.rowOneChecker = false;
		}
		if (lastMarbleIndex == PLAYER_TWO_BANK) {
			this.rowOneChecker = true;
			this.rowTwoChecker = false;
		} else if (lastMarbleIndex == PLAYER_ONE_BANK) {
			this.rowTwoChecker = true;
			this.rowOneChecker = false;
		}
	}

	public boolean rowOneBlocked() {
		return this.rowOneChecker;
	}

	public boolean rowTwoBlocked() {
		return this.rowTwoChecker;
	}

	public boolean gameOver(int[] pits) {
		int emptyPitPlayerOne = 0;
		int emptyPitPlayerTwo = 0;
		int pitsPerRow = 6;

		for (int i = 0; i < PLAYER_ONE_BANK; i++) {
			if (pits[i] == 0) {
				emptyPitPlayerOne++;
			}
		}
		for (int i = PLAYER_ONE_BANK + 1; i < PLAYER_TWO_BANK; i++) {
			if (pits[i] == 0) {
				emptyPitPlayerTwo++;
			}
		}
		if (emptyPitPlayerOne == pitsPerRow || emptyPitPlayerTwo == pitsPerRow) {
			return true;
		}
		return false;
	}

	public player winner(boolean gameOver) {

		if (gameOver) {
			int playerOneScore = pits[PLAYER_ONE_BANK];
			int playerTwoScore = pits[PLAYER_TWO_BANK];
			if (playerOneScore > playerTwoScore) {
				return player.One;
			} else if (playerOneScore < playerTwoScore) {
				return player.Two;
			}
		}
		return null;
	}


}
