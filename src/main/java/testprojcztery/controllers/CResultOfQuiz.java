package testprojcztery.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import testprojcztery.ViewManager;

public class CResultOfQuiz {
	public void setResults(int correct, int wrong) {
		pieChart.getData().clear();
		pieChart.setData(FXCollections.observableArrayList(
				new PieChart.Data("Poprawne", correct),
				new PieChart.Data("Błędne", wrong)
		));
		amountOfFlashcardsLabel.setText((correct + wrong) + "");
		correctAnswersLabel.setText(correct+"");
		wrongAnswersLabel.setText(wrong+"");
	}

	@FXML
	private void goToMainMenuButtonOnAction(ActionEvent actionEvent) {
		ViewManager.loadView("VMainMenu.fxml");
	}

	@FXML
	private Label amountOfFlashcardsLabel;
	@FXML
	private Label correctAnswersLabel;
	@FXML
	private Label wrongAnswersLabel;
	@FXML
	private PieChart pieChart;
}
