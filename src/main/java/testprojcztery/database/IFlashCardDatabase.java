package testprojcztery.database;

import javafx.collections.ObservableList;
import testprojcztery.Language;

import java.sql.SQLException;

/**
 * Interfejs pozwalający na zarządzanie fiszkami. Nie pozwalan na zarządzzanie kolekcjami fiszek
 */
public interface IFlashCardDatabase {

	boolean addFlashCard(FlashCardCollection collection, String first, String second) throws SQLException;

	boolean remove(FlashCard flashCard) throws SQLException;

	void update(FlashCard flashCard) throws SQLException;

	void updateFlashCards(ObservableList<FlashCard> flashCards);

	ObservableList<FlashCard> getFlashCards(FlashCardCollection collection) throws SQLException;

	void createCollection(String name, Language firstLanguage, Language secondLanguage) throws NoSuchLanguageException, SQLException;

	boolean remove(FlashCardCollection collection) throws SQLException;

	void update(FlashCardCollection collection) throws SQLException;

	FlashCardCollectionData getInfo(FlashCardCollection collection) throws SQLException;

	ObservableList<FlashCardCollection> getAllCollections() throws SQLException;
}
