package Oware.View.Menu;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author pietrocalzini the view representing the initial menu
 */
public class MenuView extends Stage {
	private StackPane twoPlayersPane = new StackPane();
	private StackPane onePlayerEasyPane = new StackPane();

	public MenuView() {
	}

	public void start() {
		VBox box = new VBox();
		box.getStyleClass().add("cont");
		box.setAlignment(Pos.CENTER);
		//Added link to css file
		box.getStylesheets().add("/Oware/View/Menu/menu.css");
		
		box.setStyle("-fx-background: #161616");
		Label labelName = new Label("Oware");
		labelName.getStyleClass().add("title");
		
		box.getChildren().add(labelName);

		// Define twoPlayersPane pane
		twoPlayersPane.setPrefSize(30, 50);
		twoPlayersPane.getStyleClass().add("btn");
		twoPlayersPane.setAlignment(Pos.CENTER);
		Label twoPlayersLabel = new Label("2 Players");
		twoPlayersPane.getChildren().add(twoPlayersLabel);
		box.getChildren().add(twoPlayersPane);

		// Define onePlayerEasyPane (easy) pane
		onePlayerEasyPane.setPrefSize(30, 50);
		onePlayerEasyPane.setAlignment(Pos.CENTER);
		onePlayerEasyPane.getStyleClass().add("btn");
		Label onePlayerLabel = new Label("1 Player Easy");
		onePlayerEasyPane.getChildren().add(onePlayerLabel);
		box.getChildren().add(onePlayerEasyPane);

		/*StackPane onePlayerHard = new StackPane();
		onePlayerHard.setPrefSize(30, 50);
		onePlayerHard.getStyleClass().add("btn");
		onePlayerHard.setAlignment(Pos.CENTER);
		Label twoPlayersHardLabel = new Label("1 Player Hard");
		onePlayerHard.getChildren().add(twoPlayersHardLabel);
		box.getChildren().add(onePlayerHard);*/

		Scene scene = new Scene(box);
		setScene(scene);
		setMinHeight(500);
		setMinWidth(500);
		setTitle("Oware");
		show();
	}

	public void setTwoPlayersOnMouseClickedEventListener(EventHandler<MouseEvent> eh) {
		twoPlayersPane.setOnMouseClicked(eh);
	}

	public void setOnePlayerOnMouseClickedEventListener(EventHandler<MouseEvent> eh) {
		onePlayerEasyPane.setOnMouseClicked(eh);
	}
}