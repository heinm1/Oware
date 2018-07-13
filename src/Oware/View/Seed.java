package Oware.View;

import javafx.scene.shape.Ellipse;

/**
 * A circle representing a seed.
 */
public class Seed extends Ellipse {

	// Boolean representing the state of the seed
	private boolean isActive;

	public Seed(boolean isActive) {
		super(4, 4);

		this.isActive = isActive;

		if (isActive) {
			setToActive();
		} else {
			setToInactive();
		}
	}

	public void setToActive() {
		isActive = true;
		getStyleClass().remove("seedStatusInactive");
		getStyleClass().add("seedStatusActive");
	}

	public void setToInactive() {
		isActive = false;
		getStyleClass().remove("seedStatusActive");
		getStyleClass().add("seedStatusInactive");
	}
}
