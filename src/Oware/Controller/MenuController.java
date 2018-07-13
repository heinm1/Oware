package Oware.Controller;

import Oware.View.Menu.MenuView;
import javafx.scene.input.MouseEvent;

/**
 * The controller for the main menu.
 */
public class MenuController {
	private MenuView menuView;

	public MenuController() {
		menuView = new MenuView();
		menuView.setTwoPlayersOnMouseClickedEventListener(this::handleMultiplayerStartGame);
		menuView.setOnePlayerOnMouseClickedEventListener(this::handleSigleplayerEasyStartGame);
	}

	/**
	 * Starts the view.
	 */
	public void start() {
		menuView.start();
	}

	/**
	 * Called when the Multiplayer Start Game button is clicked on.
	 *
	 * @param e The MouseEvent.
	 */
	private void handleMultiplayerStartGame(MouseEvent e) {
		MainViewController mvc = new MainViewController(1);
		mvc.start();
		menuView.close();
	}

	/**
	 * Called when the Singleplayer Easy Start Game button is clicked on.
	 *
	 * @param e The MouseEvent.
	 */
	private void handleSigleplayerEasyStartGame(MouseEvent e) {
		MainViewController mvc = new MainViewController(2);
		mvc.start();
		menuView.close();
	}

}
