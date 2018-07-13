package Oware.Events;

/**
 * Emitted when the board is updated. Does not include player seed updates.
 */
public class BoardUpdateEvent extends Event {
	private int[] seeds;

	public BoardUpdateEvent(int[] seeds) {
		this.seeds = seeds;
	}

	public int[] getSeeds() {
		return seeds;
	}
}
