package Oware.Model;

import Oware.Events.Event;
import Oware.Events.GameWonEvent;
import Oware.Events.SwapPlayersEvent;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

/**
 * The model for the Game. Manages players, the board and the turns.
 */
public class Game {
	private Board board = new Board();
	private Player currentPlayer;

	private Player player1;
	private Player player2;

	private ArrayList<Consumer<Event>> eventListeners = new ArrayList<>();
	private ArrayList<Consumer<Event>> playerEventListeners = new ArrayList<>();

	/**
	 * Class constructor that selects a random player to start and creates a new Board object
	 *
	 * @param mode The game mode.
	 *             1 - Multiplayer
	 *             2 - Singleplayer - Easy
	 *             3 - Singleplayer - Hard
	 */
	public Game(int mode) {
		// Player 1 will always be a human player
		player1 = new HumanPlayer();
		switch (mode) {
			case 1:
				// Multiplayer, Player 2 human player
				player2 = new HumanPlayer();
				break;
			case 2:
				// Singleplayer, dumb AI, player is SimpleAI
				player2 = new SimpleAIPlayer();
				break;
			// TODO: Singleplayer, smart(er) AI
		}
		player1.setEventListeners(playerEventListeners);
		player2.setEventListeners(playerEventListeners);

		// Select a starting player
		Random random = new Random();
		currentPlayer = (random.nextBoolean() ? player1 : player2);
		currentPlayer.setFirst(true);
		board.setPlayer1(player1);
		board.setPlayer2(player2);
		board.assignHouses();
		if (currentPlayer.equals(player2) && player2 instanceof SimpleAIPlayer) {
			tick(((SimpleAIPlayer) currentPlayer).makeMove());
		}
	}

	/**
	 * Runs a round of the game.
	 *
	 * @param selection The house the player decided to click on.
	 */
	public void tick(int selection) {
		int[] seedsArray = getBoard().getBoardSeeds();
		if (seedsArray[selection] == 0) {
			// No seeds, ignore click
			return;
		}

		if ((currentPlayer.equals(player1) && selection > 5)
				|| (currentPlayer.equals(player2) && selection < 6)) {
			// Illegal move
			return;
		}

		// Check if opponent has no seed
		if (currentPlayer.equals(player1)) {
			// Check player 2 seeds
			int seedCount = 0;
			for (int i = 6; i < 12; i++) {
				seedCount += seedsArray[i];
			}

			// Don't allow moves that wouldn't give seeds to opponent
			if (seedCount < 1 && (seedsArray[selection] + selection) < 6) {
				return;
			}
		} else {
			// Check player 1 seeds
			int seedCount = 0;
			for (int i = 0; i < 6; i++) {
				seedCount += seedsArray[i];
			}

			// Don't allow moves that wouldn't give seeds to opponent
			if (seedCount < 1 && (seedsArray[selection] + selection) < 12) {
				return;
			}
		}

		// Make the move
		board.makeMove(selection, currentPlayer);

		// Check victory condition
		if (player1.getSeeds() > 24) {
			notifyListeners(new GameWonEvent(true, player1));
			return;
		} else if (player2.getSeeds() > 24) {
			notifyListeners(new GameWonEvent(true, player2));
			return;
		} else if (player1.getSeeds() == 24 && player2.getSeeds() == 24) {
			notifyListeners(new GameWonEvent(false, null));
			return;
		}

		// Nobody has won, continue the game
		// Swap players
		swapPlayer();
		notifyListeners(new SwapPlayersEvent());
		// If the current player is an AI, let it make its move
		if (currentPlayer instanceof SimpleAIPlayer) {
			tick(((SimpleAIPlayer) currentPlayer).makeMove());
		}
	}

	/**
	 * Switches to the other player.
	 */
	private void swapPlayer() {
		if (currentPlayer.equals(player1)) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Board getBoard() {
		return board;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	/**
	 * Adds an event listener to the Game.
	 *
	 * @param eventListener The event listener to add.
	 */
	public void addEventListener(Consumer<Event> eventListener) {
		eventListeners.add(eventListener);
	}

	/**
	 * Adds an event listener to the Players.
	 *
	 * @param eventListener The event listener to add.
	 */
	public void addPlayerEventListener(Consumer<Event> eventListener) {
		playerEventListeners.add(eventListener);
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
