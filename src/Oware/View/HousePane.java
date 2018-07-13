package Oware.View;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * A pane representing a House.
 */
public class HousePane extends Pane {

	/**
	 * Label for seed number.
	 */
	private Label seedLabel;

	/**
	 * Number of seeds in this house.
	 */
	private int numberOfSeeds;

	/**
	 * Whether the HousePane should be shown as active (owner's turn)
	 */
	private boolean isActive = false;

	/**
	 * Panel representing the circle surrounding the seeds.
	 */
	private StackPane houseCircle;

	/**
	 * The panel to show the seeds.
	 */
	private TilePane seedsPane;

	/**
	 * The position of this House.
	 */
	private int position;

	/**
	 * The constructor.
	 *
	 * @param numberOfSeeds Number of seeds this house has initially.
	 * @param index The position of this house.
	 */
	public HousePane(int numberOfSeeds, int index) {
		this.numberOfSeeds = numberOfSeeds;
		this.position = index;

		// Set size
		setPrefSize(120, 120);
		// Link the stylesheet
		getStylesheets().add("/Oware/View/stylesheet.css");

		seedLabel = new Label("" + numberOfSeeds);
		// Draw all the components
		drawWidgets();
		// Draw all the seeds
		drawSeeds(numberOfSeeds);
		// Set to inactive by default
		setToInactive();
	}

	/**
	 * Draws the widgets.
	 */
	private void drawWidgets() {
			
		VBox container = new VBox();
		
		container.setAlignment(Pos.CENTER);
		
		houseCircle = new StackPane();
		houseCircle.setPrefSize(120, 120);
		seedsPane = new TilePane();
		seedsPane.getStyleClass().add("houseCircle");
		seedsPane.setPrefColumns(8);
		seedsPane.setVgap(2);
		seedsPane.setHgap(2);
				
		houseCircle.getChildren().add(seedsPane);

		StackPane.setAlignment(seedLabel, Pos.CENTER);
		
		container.getChildren().add(seedLabel);
		container.getChildren().add(houseCircle);
		
		this.getChildren().add(container);
	}

	/**
	 * Draws the seeds on the pane.
	 *
	 * @param numberOfSeeds The number of seeds to draw on the pane.
	 */
	private void drawSeeds(int numberOfSeeds) {
		seedsPane.getChildren().clear();
		for (int j = 0; j < numberOfSeeds; j++) {
			seedsPane.getChildren().add(new Seed(isActive));
		}
	}

	/**
	 * Toggles the active status.
	 */
	public void toggleActive() {
		if (isActive) {
			setToInactive();
		} else {
			setToActive();
		}
	}

	/**
	 * Sets the pane to active.
	 */
	public void setToActive() {
		isActive = true;
		for (Node s : seedsPane.getChildren()) {
			Seed seed = (Seed) s;
			seed.setToActive();
		}
		houseCircle.getStyleClass().remove("houseInactive");
		houseCircle.getStyleClass().add("houseActive");
		seedLabel.getStyleClass().remove("seedLabelInactive");
		seedLabel.getStyleClass().add("seedLabelInactive");
	}

	/**
	 * Sets the pane to inactive.
	 */
	public void setToInactive() {
		isActive = false;
		for (Node s : seedsPane.getChildren()) {
			Seed seed = (Seed) s;
			seed.setToInactive();
		}
		houseCircle.getStyleClass().remove("houseActive");
		houseCircle.getStyleClass().add("houseInactive");
		seedLabel.getStyleClass().remove("seedLabelActive");
		seedLabel.getStyleClass().add("seedLabelInactive");
	}

	/**
	 * Gets the number of seeds.
	 *
	 * @return int The number of seeds.
	 */
	public int getSeeds() {
		return numberOfSeeds;
	}

	/**
	 * Sets the number of seeds in this house.
	 *
	 * @param numberOfSeeds The number of seeds to set.
	 */
	public void setSeeds(int numberOfSeeds) {
		this.numberOfSeeds = numberOfSeeds;
		seedLabel.setText("" + numberOfSeeds);
		drawSeeds(numberOfSeeds);
	}

	/**
	 * Gets the position of this HousePane.
	 *
	 * @return int The position of this HousePane.
	 */
	public int getPosition() {
		return position;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}
}