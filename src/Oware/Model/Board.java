package Oware.Model;

import Oware.Events.BoardUpdateEvent;
import Oware.Events.Event;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * A Board class. Holds the Houses, and carries out movement-related operations.
 *
 * @see House The individual houses.
 */
public class Board {
	/**
	 * The board.
	 */
	private House[] board = new House[12];
	private Player player1;
	private Player player2;

	private ArrayList<Consumer<Event>> eventListeners = new ArrayList<>();

	/**
	 * Assigns the houses. Gives 6 houses to each player, and assigns their HouseVictory as well.
	 */
	public void assignHouses() {
		// Initialise the board
		for (int i = 0; i < 6; i++) {
			board[i] = new House(player1);
		}

		for (int i = 6; i < 12; i++) {
			board[i] = new House(player2);
		}
	}

	/**
	 * Takes all the seeds from one house, and distributes it to the other houses clockwise.
	 *
	 * @param houseIndex The starting index.
	 * @param player The player that is attempting to make a move.
	 */
	public void makeMove(int houseIndex, Player player) {
		int numberOfSeeds = board[houseIndex].getSeeds();
		// Clear the seeds in starting house
		board[houseIndex].setSeeds(0);

		int varHouseIndex = houseIndex;
		varHouseIndex++;

		if (varHouseIndex == 12) {
			varHouseIndex = 0;
		}

		// Distributes the seeds
		while (numberOfSeeds != 0) {
			// Our house won't get any seeds
			if (varHouseIndex != houseIndex) {
				board[varHouseIndex].addSeed();
				numberOfSeeds--;
			}
			varHouseIndex++;

			if (varHouseIndex == 12) {
				varHouseIndex = 0;
			}
		}

		// Capture the seeds
		captureSeeds(varHouseIndex - 1, houseIndex, player);
	}

	/**
	 * Captures the seeds anti-clockwise. If the seeds can be captured, they will be captured and given to the player.
	 *
	 * @param houseIndex The index to start checking from
	 * @param startingIndex The index where the move was started
	 * @param player The player attempting to capture the seeds.
	 */
	private void captureSeeds(int houseIndex, int startingIndex, Player player) {
		int capturedSeeds = 0;

		int varHouseIndex = houseIndex;
		boolean streakFound = false;

		if (varHouseIndex == -1) {
			varHouseIndex = 11;
		}

		ArrayList<Integer> toClear = new ArrayList<>();

		// Get potential capture targets
		do {
			House house = board[varHouseIndex];
			int numSeeds = house.getSeeds();

			if (numSeeds == 2 || numSeeds == 3) {
				if (!house.getPlayer().equals(player)) {
					// Capture those
					streakFound = true;
					capturedSeeds += house.getSeeds();
					toClear.add(varHouseIndex);
				}
 			} else {
				if (streakFound) {
					// Streak ended, exit
					break;
				}
			}

			varHouseIndex--;

			// Loop back
			if (varHouseIndex == -1) {
				varHouseIndex = 11;
			}
		} while (varHouseIndex != startingIndex);

		// Check if move would lead to opponent having no seeds
		if (toClear.size() > 0) {
			int hasSeedsCount = 0;
			if (player.equals(player2)) {
				for (int i = 0; i < 6; i++) {
					if (board[i].getSeeds() > 0) {
						hasSeedsCount++;
					}
				}
			} else {
				for (int i = 6; i < 12; i++) {
					if (board[i].getSeeds() > 0) {
						hasSeedsCount++;
					}
				}
			}

			// If the move is legal, continue with the seed distribution
			if (hasSeedsCount > 0) {
				// Do the capture
				int currentNumber = player.getSeeds();
				player.setSeeds(currentNumber + capturedSeeds);
				for (int i : toClear) {
					board[i].setSeeds(0);
				}
			}
		}
		notifyListeners(new BoardUpdateEvent(getBoardSeeds()));
	}

	/**
	* @return Return the array of houses (use mainly for test purposes)
	*
	*/
	public House[] getBoard(){
		return board;
	}

	/**
	 * Gets the board's seeds as an array of ints.
	 *
	 * @return The seeds array.
	 */
	public int[] getBoardSeeds() {
		int[] seeds = new int[12];
		for (int i = 0; i < seeds.length; ++i) {
			seeds[i] = board[i].getSeeds();
		}

		return seeds;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
		player1.setBoard(this);
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
		player2.setBoard(this);
	}

	/**
	 * Adds an event listener to the Board.
	 *
	 * @param eventListener The event listener to add.
	 */
	public void addEventListener(Consumer<Event> eventListener) {
		eventListeners.add(eventListener);
	}

	/**
	 * Dispatches an event.
	 *
	 * @param e The event to dispatch.
	 */
	private void notifyListeners(Event e) {
		for (Consumer<Event> c : eventListeners) {
			c.accept(e);
		}
	}
}
