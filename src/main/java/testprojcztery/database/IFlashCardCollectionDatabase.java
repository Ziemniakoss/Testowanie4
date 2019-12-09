package testprojcztery.database;

import javafx.collections.ObservableList;
import testprojcztery.Language;

public interface IFlashCardCollectionDatabase {
	void create(String name, Language firstLanguage, Language secondLanguage);

	void remove(FlashCardCollection collection);

	void update(FlashCardCollection collection);

	FlashCardCollectionData getInfo(FlashCardCollection collection);

	ObservableList<FlashCardCollection> getAllCollections();
}
