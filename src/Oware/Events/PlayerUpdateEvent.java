package Oware.Events;

import Oware.Model.Player;

/**
 * Emitted when the player has been updated.
 */
public class PlayerUpdateEvent extends Event {
	private Player player;

	public PlayerUpdateEvent(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
}
