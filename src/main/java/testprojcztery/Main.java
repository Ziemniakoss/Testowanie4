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
		primaryStage.show();
	}

	@Override
	public void init() throws Exception {
		super.init();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}
}
