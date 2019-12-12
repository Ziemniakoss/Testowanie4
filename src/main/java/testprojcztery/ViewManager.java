package testprojcztery;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import testprojcztery.controllers.CQuiz;
import testprojcztery.controllers.CResultOfQuiz;
import testprojcztery.database.FlashCard;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Klasa pozwalająca na zarządzanie tym który aktualnie widok jest
 * wyświetlany.
 */
public abstract class ViewManager {
	private static Stage primaryStage;
	private static final ResourceBundle resources;

	static {
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
	 * @return
	 * @throws IllegalStateException gdy ViewManager nie został zainicjalizowany
	 */
	public static Object loadView(String name) {
		if (primaryStage == null) {
			System.err.println("ViewManager was not initialized");
			throw new IllegalStateException("ViewManager was not initialized");
		}
		Parent root = null;
		String fileName = name;
		System.out.println("Loading view from file " + fileName);
		try {
			URL resource = ViewManager.class.getClassLoader().getResource(fileName);
			FXMLLoader loader = new FXMLLoader(resource);
			root = loader.load();
			System.out.println(loader.getController().toString());
			System.out.println(loader.getController() == null);
			Scene scene = new Scene(root, 1000, 800);
			primaryStage.setScene(scene);
			return loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;//loader.getController();
	}

	protected static ResourceBundle getResources() {
		return resources;
	}

	public static void showResults(int correctAnswers, int wrongAnswers) {
		CResultOfQuiz contr = (CResultOfQuiz) loadView("VResultOfQuiz.fxml");
		contr.setResults(correctAnswers, wrongAnswers);
	}

	public static void showQuiz(List<FlashCard> flashCards){
		CQuiz contr = (CQuiz) loadView("VQuiz.fxml");
		System.out.println(contr);
		contr.setFlashcards(flashCards);
	}
}
