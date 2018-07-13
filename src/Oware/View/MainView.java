package Oware.View;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * The game window view.
 */
public class MainView extends Stage {

	/**
	 * Whether Player1 is in control or not.
	 */
	private boolean isLeft;

	/**
	 * The houses array.
	 */
	private HousePane[] houseArray;

	/**
	 * The victory houses of both players.
	 */
	private VictoryHousePane leftVictoryHouse;
	private VictoryHousePane rightVictoryHouse;
	/**
	 * The result label. Used to display the results text.
	 */
	private Label resultLabel;

	/**
	 * The Play Again button.
	 */
	private StackPane playAgainPane = new StackPane();

	/**
	 * Whether the view has been locked.
	 */
	private boolean locked = false;

	/**
	 * Initialises the view.
	 */
	public MainView() {
		super();

		BorderPane border = new BorderPane();
		border.getStylesheets().add("/Oware/View/stylesheet.css");
		border.getStyleClass().add("gameWindow");
		VBox center = new VBox();
		center.setAlignment(Pos.CENTER);

		GridPane houses = new GridPane();
		houses.setVgap(50);
		houses.getStyleClass().add("housesGrid");
		houseArray = new HousePane[12];

		// Adding all the houses for player 1
		HBox topBox = new HBox();
		topBox.setSpacing(20);
		for (int i = 0; i < 6; i++) {
			HousePane p = new HousePane(4, 5 - i);
			houseArray[5 - i] = p;
			topBox.getChildren().add(p);
		}

		houses.add(topBox, 0, 0);
		center.getChildren().add(houses);

		// Adding all the houses for player 2
		HBox bottomBox = new HBox();
		bottomBox.setSpacing(20);
		for (int i = 6; i < 12; i++) {
			HousePane p = new HousePane(4, i);
			houseArray[i] = p;
			bottomBox.getChildren().add(p);
		}

		//Add label to display winner
		VBox winnerBox = new VBox();
		winnerBox.setAlignment(Pos.CENTER);
		resultLabel = new Label("");
		resultLabel.getStyleClass().add("resultsText");
		winnerBox.getChildren().add(resultLabel);
		houses.add(winnerBox, 0, 1);
		houses.add(bottomBox, 0, 2);

		border.setCenter(center);

		VBox leftBox = new VBox();
		leftBox.setAlignment(Pos.CENTER);
		// Point house for player one
		leftVictoryHouse = new VictoryHousePane(0);
		leftBox.getChildren().add(leftVictoryHouse);
		border.setLeft(leftBox);

		VBox rightBox = new VBox();
		rightBox.setAlignment(Pos.CENTER);
		// Point house for player two
		rightVictoryHouse = new VictoryHousePane(0);
		rightBox.getChildren().add(rightVictoryHouse);

		playAgainPane.getStyleClass().add("btn");
		playAgainPane.setAlignment(Pos.CENTER);
		playAgainPane.setMaxWidth(100.0);
		playAgainPane.getChildren().add(new Label("Play Again"));
		playAgainPane.setVisible(false);
		winnerBox.getChildren().add(playAgainPane);

		border.setRight(rightBox);

		Scene scene = new Scene(border);
		scene.getStylesheets().add("/Oware/View/stylesheet.css");
		setScene(scene);
		setMinHeight(550);
		setMinWidth(800);
		setResizable(false);
		setTitle("Oware");
	}

	public void start() {
		initActiveHousePanes();
		show();
	}

	public void setHouseSeeds(int house, int seeds) {
		houseArray[house].setSeeds(seeds);
	}

	public void setPoints(int points, boolean isLeft) {
		if (isLeft) {
			leftVictoryHouse.setPoints(points);
		} else {
			rightVictoryHouse.setPoints(points);
		}
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setIsLeft(boolean left) {
		isLeft = left;
	}

	public void initActiveHousePanes() {
		if (isLeft) {
			for (int i = 0; i < 12; i++) {
				if (i < 6) {
					houseArray[i].setToActive();
				}
			}
			leftVictoryHouse.setToActive();
		} else {
			for (int i = 0; i < 12; i++) {
				if (i > 5) {
					houseArray[i].setToActive();
				}
			}
			rightVictoryHouse.setToActive();
		}
	}

	public void setOnHouseClickEventListener(EventHandler<MouseEvent> eh) {
		for (HousePane i : houseArray) {
			i.setOnMouseClicked(eh);
		}
	}

	/**
	 * Toggles the houses.
	 */
	public void swapActiveHouses() {
		for (HousePane h : houseArray) {
			h.toggleActive();
		}
		leftVictoryHouse.toggleActive();
		rightVictoryHouse.toggleActive();
	}

	/**
	 * Sets the winner of the game.
	 *
	 * @param winner integer representing the 3 final outcomes of the game
	 *               - 0 - draw
	 *               - 1 - player 1 wins
	 *               - 2 - player 2 wins
	 */
	public void setWinner(int winner) {
		if (winner == 0) {
			resultLabel.setText("Draw");
		} else if (winner == 1) {
			resultLabel.setText("Player 1 wins!");
		} else {
			resultLabel.setText("Player 2 wins!");
		}
	}

	/**
	 * Disables all houses.
	 */
	public void disableAllHouses() {
		for (HousePane h : houseArray) {
			h.setToInactive();
		}
		locked = true;
	}

	/**
	 * Shows the Play Again button.
	 */
	public void showPlayAgainButton() {
		playAgainPane.setVisible(true);
	}

	public boolean isLocked() {
		return locked;
	}

	/**
	 * Sets the event listener on game close.
	 */
	public void setOnCloseGameClickEventListener(EventHandler<MouseEvent> eh) {
		playAgainPane.setOnMouseClicked(eh);
	}
}
