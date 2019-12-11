package testprojcztery.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
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
	@FXML
	private TextField collectionNameTextField;
	@FXML
	private ProgressBar levelOneProgressBar;
	@FXML
	private Label levelOneLabel;
	@FXML
	private Label levelTwoLabel;
	@FXML
	private Label levelThreeLabel;
	@FXML
	private ProgressBar levelTwoProgressBar;
	@FXML
	private ProgressBar levelThreeProgressBar;
	@FXML
	private Label levelFourLabel;
	@FXML
	private ProgressBar levelFourProgressBar;
	@FXML
	private Label levelFiveLabel;
	@FXML
	private ProgressBar levelFiveProgressBar;
	@FXML
	private Label levelSixLabel;
	@FXML
	private ProgressBar levelSixProgressBar;
	@FXML
	private Label levelSevenLabel;
	@FXML
	private ProgressBar levelSevenProgressBar;

	/**
	 * Jeżeli enter i pole nie jest puste to tworzy nową kolekcję fiszek
	 *
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

	@FXML
	private void collectionNameTextFieldOnKeyReleased(KeyEvent keyEvent) {


	}

	@FXML
	private void manageFlashcardsButtonOnAction(ActionEvent actionEvent) {

	}

	@FXML
	private void playButtonOnAction(ActionEvent actionEvent) {

	}
}
