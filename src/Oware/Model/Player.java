package Oware.Model;

import Oware.Events.Event;
import Oware.Events.PlayerUpdateEvent;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * A Player model.
 */
public abstract class Player {
	/**
	 * The Board object.
	 */
	protected Board board;

	/**
	 * Whether this player is going first.
	 */
	private boolean first = false;

	/**
	 * The amount of seeds this Player has.
	 */
	private int seeds = 0;

	/**
	 * An ArrayList of event listeners.
	 */
	private ArrayList<Consumer<Event>> eventListeners = new ArrayList<>();

	/**
	 * Whether this player goes first.
	 *
	 * @return boolean
	 */
	public boolean isFirst() {
		return first;
	}

	/**
	 * Sets the player's first-turn status.
	 *
	 * @param first Whether this player should go first.
	 */
	public void setFirst(boolean first) {
		this.first = first;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getSeeds() {
		return seeds;
	}

	public void setSeeds(int seeds) {
		this.seeds = seeds;
		notifyListeners();
	}

	public void setEventListeners(ArrayList<Consumer<Event>> a) {
		this.eventListeners = a;
	}

	private void notifyListeners() {
		for (Consumer<Event> c : eventListeners) {
			c.accept(new PlayerUpdateEvent(this));
		}
	}
}