package testprojcztery.database;

import javafx.collections.ObservableList;
import testprojcztery.Language;

import java.sql.SQLException;

/**
 * Interfejs pozwalający na zarządzanie fiszkami. Nie pozwalan na zarządzzanie kolekcjami fiszek
 */
public interface IFlashCardDatabase {

	void addFlashCard(FlashCardCollection collection, String first, String second);

	void removeFlashCard(FlashCard flashCard);

	void updateFlashCard(FlashCard flashCard);

	void updateFlashCards(ObservableList<FlashCard> flashCards);

	ObservableList<FlashCard> getFlashCards(FlashCardCollection collection);

	void createCollection(String name, Language firstLanguage, Language secondLanguage) throws NoSuchLanguageException, SQLException;

	void remove(FlashCardCollection collection);

	void update(FlashCardCollection collection) throws SQLException;

	FlashCardCollectionData getInfo(FlashCardCollection collection);

	ObservableList<FlashCardCollection> getAllCollections();
}
