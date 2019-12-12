package testprojcztery.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import testprojcztery.ViewManager;
import testprojcztery.database.FlashCard;
import testprojcztery.database.SqliteFlashcardsDatabase;

import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class CQuiz implements Initializable {
	private List<FlashCard> flashCards;
	private SimpleBooleanProperty showed = new SimpleBooleanProperty(true);
	private int index = 0;
	private int correctAnswers = 0;
	private int wrongAnswers = 0;
	private FlashCard displayed;
	private SqliteFlashcardsDatabase db;

	Iterator<FlashCard> iterator;


	public void setFlashcards(List<FlashCard> flashCards) {
		this.flashCards = flashCards;
		showed = new SimpleBooleanProperty(true);
		iterator = flashCards.iterator();
		try {
			db = SqliteFlashcardsDatabase.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		showNext();
	}


	private void showNext() {
		if (iterator.hasNext()) {
			index++;
			displayed = iterator.next();
			showed.set(false);
			secondLabel.setText("????????????????");
			firstLabel.setText(displayed.getFirst());
			progressBar.setProgress(1.0 * (index - 1) / flashCards.size());
		} else {
			ViewManager.showResults(correctAnswers, wrongAnswers);
		}
	}


	@FXML
	private void showSecond(ActionEvent actionEvent) {
		showed.set(!showed.getValue());
		secondLabel.setText(displayed.getSecond());
	}

	@FXML
	private void okButtonOnAction(ActionEvent actionEvent) {
		displayed.increaseLevel();
		try {
			db.update(displayed);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		correctAnswers++;
		showNext();
	}

	@FXML
	private void noButtonOnAction(ActionEvent actionEvent) {
		displayed.resetLevel();
		try {
			db.update(displayed);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		wrongAnswers++;
		showNext();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		showed.addListener((e, oldValue, newValue) -> {
			okButton.setDisable(!newValue);
			noButton.setDisable(!newValue);
			System.out.println("hekadaafsfafasfa");
		});
	}

	@FXML
	private Label firstLabel;
	@FXML
	private Label secondLabel;
	@FXML
	private Button okButton;
	@FXML
	private Button noButton;
	@FXML
	private ProgressBar progressBar;
}
