package testprojcztery.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import testprojcztery.Language;
import testprojcztery.ViewManager;
import testprojcztery.database.*;

import javax.swing.text.View;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.ResourceBundle;

public class CMainMenu implements Initializable {
	private FlashCard displayedFlashcard;
	private FlashCardCollection displayedCollection;
	private SqliteFlashcardsDatabase db;
	private SimpleBooleanProperty modified = new SimpleBooleanProperty(false);//Czy fiszka była zmodyfikowana


	/**
	 * Jeżeli enter i pole nie jest puste to tworzy nową kolekcję fiszek
	 *
	 * @param keyEvent
	 */
	@FXML
	private void newCollectionTextFieldOnKeyReleased(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.ENTER) {
			newCollectionButtonOnAction(null);
		}
	}

	@FXML
	private void newCollectionButtonOnAction(ActionEvent actionEvent) {
		Stage dialog = new Stage();

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10));

		TextField name = new TextField();
		name.setPromptText("Nazwa");
		if (newCollectionTextField.getText().length() > 0) {
			name.setText(newCollectionTextField.getText());
		}
		grid.add(name, 0, 0, 2, 1);

		Label flan = new Label("Pierwszy język:");
		grid.add(flan, 0, 1);
		ComboBox<Language> firstLanguage = new ComboBox<>();
		firstLanguage.setItems(FXCollections.observableArrayList(new ArrayList<>(EnumSet.allOf(Language.class))));
		grid.add(firstLanguage, 1, 1);

		grid.add(new Label("Drugi język"), 0, 2);
		ComboBox<Language> secondLanguage = new ComboBox<>();
		secondLanguage.setItems(FXCollections.observableArrayList(new ArrayList<>(EnumSet.allOf(Language.class))));
		grid.add(secondLanguage, 1, 2);

		Button addCollection = new Button("Dodaj");
		addCollection.setOnAction(e -> {
			if (secondLanguage.getSelectionModel().getSelectedItem() != null
					&& firstLanguage.getSelectionModel().getSelectedItem() != null
					&& name.getText().length() > 0) {
				try {
					db.createCollection(name.getText(), firstLanguage.getValue(), secondLanguage.getValue());
					update();
				} catch (NoSuchLanguageException | SQLException ex) {
					System.err.println("Błąd prz tworzeniu kolekcji: " + ex.getMessage());
					dialog.close();
					dialog.hide();
				}
			}
		});
		grid.add(addCollection,1,3);

		dialog.setScene(new Scene(grid, 600, 600));
		dialog.setAlwaysOnTop(true);

		dialog.show();

	}

	@FXML
	private void playButtonOnAction(ActionEvent actionEvent) {
		ViewManager.showQuiz(flashcardsListView.getItems());
	}

	@FXML
	private void flashcardModified(ActionEvent actionEvent) {
		modified.set(true);

	}

	@FXML
	private void newFlashcardButtonOnAction(ActionEvent actionEvent) {
		try{
			db.addFlashCard(displayedCollection, newFlashcardFirstTextField.getText(),newFlashcardSecondTextField.getText());
			flashcardsListView.getItems().clear();
			flashcardsListView.setItems(db.getFlashCards(displayedCollection));
			if(flashcardsListView.getItems().size() > 1){
				displayFlashcard(flashcardsListView.getItems().get(0));
			}else {
				displayFlashcard(null);
			}
			newFlashcardFirstTextField.setText("");
			newFlashcardSecondTextField.setText("");
		} catch (SQLException | IllegalArgumentException | NullPointerException e) {
			System.err.println("Błąd przy tworzeniu nowej fiszki: "+e.getMessage());
		}
	}

	@FXML
	private void saveFlashcard(ActionEvent actionEvent) {
		if (displayedFlashcard == null || !modified.get()) {
			return;
		}
		try {
			db.update(displayedFlashcard);
			modified.set(false);
		} catch (SQLException e) {
			System.err.println("Błąd wystąpił podczas zapisywania zmodyfikowanej fiszki: " + e.getMessage());
		}
	}

	@FXML
	private void deleteFlashcard(ActionEvent actionEvent) {
		if (displayedFlashcard == null) {
			return;
		}
		try {
			db.remove(displayedFlashcard);
			System.out.println("Usunięto fiszkę " + displayedFlashcard.getId());
			displayFlashcard(null);
			flashcardsListView.setItems(db.getFlashCards(displayedCollection));
		} catch (SQLException e) {
			System.err.println("Wystąpił błąd podczas usuwania fiszki " + displayedFlashcard.getId());
		}
	}

	private void displayFlashcard(FlashCard flashCard) {
		displayedFlashcard = flashCard;
		if (flashCard == null) {
			firstTextField.setDisable(true);
			firstTextField.setText("");
			secondTextField.setText("");
			secondTextField.setDisable(true);
			modified.set(false);
			flashcardLevelLabel.setText("");
			deleteFlashcardButton.setDisable(true);

		} else {
			flashcardLevelLabel.setText(flashCard.getLevel() + "");
			firstTextField.setDisable(false);
			firstTextField.setText(flashCard.getFirst());
			secondTextField.setDisable(false);
			secondTextField.setText(flashCard.getSecond());
			modified.set(false);
			deleteFlashcardButton.setDisable(false);
		}
	}

	private void displayCollection(FlashCardCollection collection) {
		displayedCollection = collection;
		flashcardsListView.getItems().clear();
		if (collection == null) {
			newFlashcardFirstTextField.setDisable(true);
			newFlashcardSecondTextField.setDisable(true);
			newFlashcardButton.setDisable(true);
			collectionNameLabel.setText("");
			collectionNameLabel.setDisable(true);
			playButton.setDisable(true);
			levelOneLabel.setText("");
			levelTwoLabel.setText("");
			levelThreeLabel.setText("");
			levelFourLabel.setText("");
			levelFiveLabel.setText("");
			levelSixLabel.setText("");
			levelSevenLabel.setText("");


			levelOneProgressBar.setProgress(1.0);
			levelTwoProgressBar.setProgress(0);
			levelThreeProgressBar.setProgress(0);
			levelFourProgressBar.setProgress(0);
			levelFiveProgressBar.setProgress(0);
			levelSixProgressBar.setProgress(0);
			levelSevenProgressBar.setProgress(0);
			flashcardsListView.getItems().clear();
		} else {
			newFlashcardFirstTextField.setDisable(false);
			newFlashcardSecondTextField.setDisable(false);
			newFlashcardButton.setDisable(false);
			collectionNameLabel.setDisable(false);
			playButton.setDisable(false);
			try {
				FlashCardCollectionData data = db.getInfo(collection);
				System.out.println(data);
				collectionNameLabel.setText(collection.getName());
				levelOneLabel.setText(data.getCardsOnLevelOne() + "");
				levelTwoLabel.setText(data.getCardsOnLevelTwo() + "");
				levelThreeLabel.setText(data.getCardsOnLevelThree() + "");
				levelFourLabel.setText(data.getCardsOnLevelFour() + "");
				levelFiveLabel.setText(data.getCardsOnLevelFive() + "");
				levelSixLabel.setText(data.getCardsOnLevelSix() + "");
				levelSevenLabel.setText(data.getCardsOnLevelSeven() + "");

				levelOneProgressBar.setProgress(1.0 * data.getCardsOnLevelOne() / data.getTotalCards());
				levelTwoProgressBar.setProgress(1.0 * data.getCardsOnLevelTwo() / data.getTotalCards());
				levelThreeProgressBar.setProgress(1.0 * data.getCardsOnLevelThree() / data.getTotalCards());
				levelFourProgressBar.setProgress(1.0 * data.getCardsOnLevelFour() / data.getTotalCards());
				levelFiveProgressBar.setProgress(1.0 * data.getCardsOnLevelFive() / data.getTotalCards());
				levelSixProgressBar.setProgress(1.0 * data.getCardsOnLevelSix() / data.getTotalCards());
				levelSevenProgressBar.setProgress(1.0 * data.getCardsOnLevelSeven() / data.getTotalCards());
				flashcardsListView.setItems(db.getFlashCards(collection));
			} catch (SQLException e) {
				System.err.println("Wystąpił błąd podczas pobierania danych o kolekcji " + collection.getId());
			}
		}
		displayFlashcard(null);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			db = SqliteFlashcardsDatabase.getInstance();
//			System.out.println(db.getInfo(db.getAllCollections().get(0)).getTotalCards());
			modified.set(true);//Bo inaczej nie wywoal się event zmiany wartości
			modified.addListener((e, oldValue, newValue) -> {
				saveChangesButton.setDisable(!newValue);
				System.out.println("hhhhhhhh");
			});
			displayFlashcard(null);
			displayCollection(null);
			update();
		} catch (SQLException e) {
			System.err.println("Błąd podczas łączenia się z bazą danych: " + e.getMessage());
			e.printStackTrace();
		}
		newCollectionTextField.requestFocus();
		flashcardsListView.setOnMouseClicked(e -> {
			FlashCard selected = flashcardsListView.getSelectionModel().getSelectedItem();
			if (selected != null && !selected.equals(displayedFlashcard)) {
				displayFlashcard(selected);
			}
		});
		collectionListView.setOnMouseClicked(e -> {
			FlashCardCollection selected = collectionListView.getSelectionModel().getSelectedItem();
			if (selected != null && !selected.equals(displayedCollection)) {
				displayCollection(selected);
			}
		});
	}

	/**
	 * Pobiera liste kolekcji z bazy, wyświetla ją
	 */
	private void update() throws SQLException {
		ObservableList<FlashCardCollection> collections = db.getAllCollections();
		collectionListView.setItems(collections);
		if (collections.size() > 0) {
			displayCollection(collections.get(0));
		}
	}


	//Elementy Gui
	@FXML
	private Button newCollectionButton;
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
	@FXML
	private TextField secondTextField;
	@FXML
	private TextField newFlashcardFirstTextField;
	@FXML
	private TextField newFlashcardSecondTextField;
	@FXML
	private Label flashcardLevelLabel;
	@FXML
	private TextField firstTextField;
	@FXML
	private Button newFlashcardButton;
	@FXML
	private Button deleteFlashcardButton;
	@FXML
	private Button saveChangesButton;
	@FXML
	private ListView<FlashCard> flashcardsListView;
	@FXML
	private Label collectionNameLabel;
	@FXML
	private ListView<FlashCardCollection> collectionListView;
	@FXML
	private TextField newCollectionTextField;
	@FXML
	private Button playButton;
}
