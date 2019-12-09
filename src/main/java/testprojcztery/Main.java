package testprojcztery;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.text.View;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ViewManager.setPrimaryStage(primaryStage);
		ViewManager.loadView("VLoadingScreen.fxml");
		//TODO Łączenie z bazą danych itp
		ViewManager.loadView("VMainMenu.fxml");
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}
}
