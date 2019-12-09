package testprojcztery.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CMainMenu {
	@FXML
	private PieChart flashcardsPieChart;
	@FXML
	private TextField newCollectionTextField;
	@FXML
	private Button newCollectionButton;
	@FXML
	private ListView collectionListView;

	/**
	 * Jeżeli enter i pole nie jest puste to tworzy nową kolekcję fiszek
	 * @param keyEvent
	 */
	@FXML
	private void newCollectionTextFieldOnKeyReleased(KeyEvent keyEvent) {
		//TODO
	}

	@FXML
	private void newCollectionButtonOnKeyReleased(KeyEvent keyEvent) {
		//TODO
	}
}
