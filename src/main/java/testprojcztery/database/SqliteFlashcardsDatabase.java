package testprojcztery.database;

import javafx.collections.ObservableList;
import testprojcztery.Language;

import java.sql.*;

/**
 * Baza fiszek używająca jako silnika Sqlite. Singleton
 */
public class SqliteFlashcardsDatabase implements IFlashCardDatabase {
	private static final String connectionString = "jdbc:sqlite:flashcard.db";
	private final static SqliteFlashcardsDatabase instance = new SqliteFlashcardsDatabase();

	public static SqliteFlashcardsDatabase getInstance() {
		return instance;
	}

	private SqliteFlashcardsDatabase() {
	}

	/**
	 * Sprawdzanie czy istnieją niezbędne tabele i ewentualne
	 * tworzenie ich
	 */
	public void init() {
		//todo
	}

	@Override
	public void createCollection(String name, Language firstLanguage, Language secondLanguage) throws NoSuchLanguageException, SQLException {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("illegal name of collection");
		}
		int firstLanguageId = getLanguageId(firstLanguage);
		int secondLanguageId = getLanguageId(secondLanguage);
		String sql = "INSERT INTO TABLE flashcard_collection (name, first_language, second_language) values (?, ?, ?);";
		try (Connection c = getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,name);
			ps.setInt(2,firstLanguageId);
			ps.setInt(3,secondLanguageId);
			ps.execute();
		}
	}

	@Override
	public void remove(FlashCardCollection collection) {


	}

	@Override
	public void update(FlashCardCollection collection) throws SQLException {
		String sql = "UPDATE flashcard_collection name = ? where id = ? LIMIT 1;";
		try(Connection c = getConnection()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, collection.getName());
			ps.setInt(2,collection.getId());
			ps.execute();
		}
	}

	@Override
	public FlashCardCollectionData getInfo(FlashCardCollection collection) {
		return null;
	}

	@Override
	public ObservableList<FlashCardCollection> getAllCollections() {
		return null;
	}

	@Override
	public void addFlashCard(FlashCardCollection collection, String first, String second) {

	}

	@Override
	public void removeFlashCard(FlashCard flashCard) {

	}

	@Override
	public void updateFlashCard(FlashCard flashCard) {

	}

	@Override
	public void updateFlashCards(ObservableList<FlashCard> flashCards) {

	}

	@Override
	public ObservableList<FlashCard> getFlashCards(FlashCardCollection collection) {
		return null;
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(connectionString);
	}

	private int getLanguageId(Language language) throws NoSuchLanguageException, SQLException {
		try (Connection c = getConnection()) {
			String sql = "SELECT id FROM languages WHERE name = ?;";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, language.toString());
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				return result.getInt("id");
			} else {
				throw new NoSuchLanguageException(language);
			}
		}
	}
}
