package testprojcztery;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ViewManager.setPrimaryStage(primaryStage);
		ViewManager.loadView("VLoadingScreen.fxml");
		ViewManager.loadView("VMainMenu.fxml");
		primaryStage.show();
	}
}
