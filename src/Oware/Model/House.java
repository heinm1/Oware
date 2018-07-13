package Oware.Model;

/**
 * Represents a house (a slot) in the board.
 */

public class House {
	/**
	 * The seeds this house has.
	 */
	private int seeds;

	/**
	 * The player that owns this house.
	 */
	private Player player;

	/**
	 * Class constructor, sets the player object in the class
	 *
	 * @param player the player that owns the house
	 */
	public House(Player player) {
		this.player = player;
		seeds = 4;
	}

	/**
	 * Getter method for the number of seeds in the house at the time
	 *
	 * @return seeds the number of seeds in the house
	 */
	public int getSeeds() {
		return seeds;
	}

	/**
	 * Sets the seeds in the house
	 *
	 * @param i an integer of the number of seeds to be in the house
	 */
	public void setSeeds(int i) {
		seeds = i;
	}

	/**
	 * Getter method for the player owning the house
	 *
	 * @return Player the player that owns the house
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * toString method showing the number of seeds in the house
	 *
	 * @return string The number of seeds in the house.
	 */
	public String toString() {
		return Integer.toString(seeds);
	}

	/**
	 * Increments the seeds in the house by one
	 */
	//increases the seeds field in HouseAbs by 1
	public void addSeed() {
		seeds++;
	}

	/**
	 * Decreases the seeds in the house by one
	 */
	//decreases the seeds field in HouseAbs by 1
	public void removeSeed() {
		seeds--;
	}

}
