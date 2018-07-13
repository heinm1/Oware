import Oware.Controller.MenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MenuController menu = new MenuController();
		menu.start();
	}

}
