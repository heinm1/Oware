package Oware.Events;

import Oware.Model.Player;

/**
 * Emitted for when a game is won (or a draw is found)
 */
public class GameWonEvent extends Event {
	private boolean hasWinner;
	private Player winner;

	public GameWonEvent(boolean hasWinner, Player winner) {
		this.hasWinner = hasWinner;
		this.winner = winner;
	}

	public boolean isHasWinner() {
		return hasWinner;
	}

	public Player getWinner() {
		return winner;
	}
}
