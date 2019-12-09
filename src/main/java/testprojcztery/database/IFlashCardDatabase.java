package testprojcztery.database;

import javafx.collections.ObservableList;

public interface IFlashCardDatabase {

	void addFlashCard(int collectionId, String first, String second);

	void removeFlashCard(FlashCard flashCard);

	void updateFlashCard(FlashCard flashCard);

	ObservableList<FlashCard> getFlashCards(FlashCardCollection collection);
}
