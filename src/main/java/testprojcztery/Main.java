package testprojcztery;

import javafx.application.Application;
import javafx.stage.Stage;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {

	public static void main(String[] args) {
		try{
			Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ViewManager.setPrimaryStage(primaryStage);
		ViewManager.loadView("VLoadingScreen.fxml");
		ViewManager.loadView("VMainMenu.fxml");
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}
}
