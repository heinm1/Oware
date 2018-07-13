package Oware.Controller;

import Oware.Events.*;
import Oware.Model.Game;
import Oware.Model.SimpleAIPlayer;
import Oware.View.HousePane;
import Oware.View.MainView;
import javafx.scene.input.MouseEvent;

/**
 * The controller for the MainView (Game window).
 */
public class MainViewController {
	private Game game;
	private MainView view;

	public MainViewController(int mode) {
		game = new Game(mode);
		game.addEventListener(this::onGameUpdate);
		game.addPlayerEventListener(this::onPlayerUpdate);
		game.getBoard().addEventListener(this::onBoardUpdate);
		view = new MainView();
		if (game.getCurrentPlayer().equals(game.getPlayer1())) {
			view.setIsLeft(true);
		} else {
			view.setIsLeft(false);
		}
		view.initActiveHousePanes();
		view.setOnHouseClickEventListener(this::onHouseClick);
		view.setOnCloseGameClickEventListener(this::onCloseGameClick);
	}

	/**
	 * Starts the view.
	 */
	public void start() {
		view.start();
	}

	/**
	 * Called when the close game button is clicked on.
	 * @param e
	 */
	public void onCloseGameClick(MouseEvent e) {
		MenuController mc = new MenuController();
		mc.start();
		game = null;
		view.close();
	}

	/**
	 * Called when a HousePane is clicked on.
	 *
	 * @param e The MouseEvent.
	 */
	private void onHouseClick(MouseEvent e) {
		if (view.isLocked()) {
			// View is locked
			return;
		}

		HousePane housePane = (HousePane) e.getSource();
		int position = housePane.getPosition();

		if (game.getCurrentPlayer() instanceof SimpleAIPlayer) {
			// AI player, ignore click - should never happen
			return;
		}

		game.tick(position);
	}

	/**
	 * Called when the board is updated.
	 *
	 * @param e The event.
	 */
	private void onBoardUpdate(Event e) {
		if (e instanceof BoardUpdateEvent) {
			BoardUpdateEvent event = (BoardUpdateEvent) e;
			int[] seeds = event.getSeeds();
			for (int i = 0; i < seeds.length; i++) {
				view.setHouseSeeds(i, seeds[i]);
			}
		}
	}

	/**
	 * Called when a player is updated.
	 *
	 * @param e The event.
	 */
	private void onPlayerUpdate(Event e) {
		if (e instanceof PlayerUpdateEvent) {
			PlayerUpdateEvent event = (PlayerUpdateEvent) e;
			int seedsCaptured = event.getPlayer().getSeeds();
			view.setPoints(seedsCaptured, event.getPlayer().isFirst());
		}
	}

	/**
	 * Called when the Game is updated.
	 *
	 * @param e The event.
	 */
	private void onGameUpdate(Event e) {
		if (e instanceof GameWonEvent) {
			GameWonEvent event = (GameWonEvent) e;
			if (event.isHasWinner()) {
				if (event.getWinner().isFirst()) {
					// Player 1 won
					view.setWinner(1);
				} else {
					// Player 2 won
					view.setWinner(2);
				}
			} else {
				// Draw
				view.setWinner(0);
			}

			view.disableAllHouses();
			view.showPlayAgainButton();
		} else if (e instanceof SwapPlayersEvent) {
			view.swapActiveHouses();
		}
	}
}
