package Oware.View;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * The HousePane for the players' seeds.
 */
public class VictoryHousePane extends StackPane {

	// Label representing the seeds in the house
	private Label pointLabel;
	// Number of seeds
	private int numberOfPoints;
	// Boolean indicating if the house is active (belongs to the player
	// currently playing the turn)
	private boolean isActive = false;

	public VictoryHousePane(int numberOfPoints) {
		this.numberOfPoints = numberOfPoints;

		// Set size
		setPrefSize(120, 120);
		// Link to the stylesheet
		getStylesheets().add("/Oware/View/stylesheet.css");
		// Draw all the components
		drawWidgets();
		// Set to inactive by default
		setToInactive();
	}

	private void drawWidgets() {
		pointLabel = new Label("" + numberOfPoints);
		this.getChildren().add(pointLabel);
	}

	/**
	 * Change the status of a house from active to inactive
	 */
	public void toggleActive() {
		if (isActive) {
			setToInactive();
		} else {
			setToActive();
		}
	}

	public void setToActive() {
		isActive = true;
		getStyleClass().remove("houseVictoryInactive");
		getStyleClass().add("houseVictoryActive");
		pointLabel.getStyleClass().remove("pointLabelInactive");
		pointLabel.getStyleClass().add("pointLabelActive");
	}

	public void setToInactive() {
		isActive = false;
		getStyleClass().remove("houseVictoryActive");
		getStyleClass().add("houseVictoryInactive");
		pointLabel.getStyleClass().remove("pointLabelActive");
		pointLabel.getStyleClass().add("pointLabelInactive");
	}

	/**
	 * Set the number of points in a victoryHouse.
	 *
	 * @param numberOfPoints The number of points to set.
	 */
	public void setPoints(int numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
		pointLabel.setText("" + this.numberOfPoints);
	}
}