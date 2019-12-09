package testprojcztery;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa pozwalająca na zarządzanie tym który aktualnie widok jest
 * wyświetlany.
 */
public abstract class ViewManager {
	private static Stage primaryStage;
	private static final Logger log;
	private static final ResourceBundle resources;
	static {
		log = Logger.getLogger(ViewManager.class.getName());
		resources = ResourceBundle.getBundle("text");
	}

	public static void setPrimaryStage(Stage primaryStage) {
		ViewManager.primaryStage = primaryStage;
		primaryStage.setTitle(resources.getString("windowTitle"));
	}

	/**
	 * Ładuje widok o podanej nazwie.
	 *
	 * @param name nazwa pliku fxml w którym przechowywany jest widok
	 * @throws IllegalStateException gdy ViewManager nie został zainicjalizowany
	 */
	public static void loadView(String name) {
		if (primaryStage == null) {
			log.log(Level.SEVERE, "ViewManager was not initialized");
			throw new IllegalStateException("ViewManager was not initialized");
		}
		Parent root = null;
		String fileName = name;
		log.info("Loading view from file " + fileName);
		try {
			URL resource = ViewManager.class.getClassLoader().getResource(fileName);
			System.out.println(resource);
			root = FXMLLoader.load(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 800);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(ViewManager.class.getClassLoader().getResource("styles" + File.separator + "dark.css").toExternalForm());
	}

	protected static ResourceBundle getResources() {
		return resources;
	}
}
