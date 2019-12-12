package testprojcztery.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import testprojcztery.Language;

import java.sql.*;
import java.util.ArrayList;

/**
 * Baza fiszek używająca jako silnika Sqlite. Singleton
 */
public class SqliteFlashcardsDatabase implements IFlashCardDatabase {
	private static final String connectionString = "jdbc:sqlite:flashcard.db";
	private static SqliteFlashcardsDatabase instance;


	public static SqliteFlashcardsDatabase getInstance() throws SQLException {
		if (instance == null) {
			instance = new SqliteFlashcardsDatabase();
		}
		return instance;
	}

	/**
	 * Tworzy wszystkie table jeżeli nie istnieją
	 */
	private SqliteFlashcardsDatabase() throws SQLException {
		//Sprawdzanie czy istenieją wszstkie tabele
		createLanguagesTable();
		createCollectionsTable();
		createFlashcardsTable();
		String sql = "INSERT OR IGNORE INTO languages (name) VALUES ('ENGLISH'), ('POLISH'), ('GERMAN');";
		try (Connection c = getConnection()) {
			c.prepareStatement(sql).execute();
		}
	}

	@Override
	public void createCollection(String name, Language firstLanguage, Language secondLanguage) throws NoSuchLanguageException, SQLException {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("illegal name of collection");
		}
		int firstLanguageId = getLanguageId(firstLanguage);
		int secondLanguageId = getLanguageId(secondLanguage);
		String sql = "INSERT INTO flashcard_collection (name, first_language, second_language) values (?, ?, ?);";
		try (Connection c = getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, firstLanguageId);
			ps.setInt(3, secondLanguageId);
			ps.execute();
		}
	}

	/**
	 * Usuwa kolekcję z bazy danych
	 *
	 * @param collection kloekcja do usunięcia
	 * @return true jak sie uda
	 */
	@Override
	public boolean remove(FlashCardCollection collection) throws SQLException {
		String sql = "DELETE FROM collections WHERE id = ? limit 1;";
		try (Connection c = getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, collection.getId());
			if (ps.executeUpdate() != 0) {
				System.out.println("Removed collection " + collection.getName());
				return true;
			}
		}
		return false;
	}

	@Override
	public void update(FlashCardCollection collection) throws SQLException {
		String sql = "UPDATE collections set name = ? where id = ? LIMIT 1;";
		try (Connection c = getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, collection.getName());
			ps.setInt(2, collection.getId());
			ps.execute();
		}
	}

	@Override
	public FlashCardCollectionData getInfo(FlashCardCollection collection) throws SQLException {//TODO
		String sql = "SELECT COUNT(*) AS amount, level FROM flashcards WHERE collection = ?";
		try (Connection c = getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, collection.getId());
			ResultSet rs = ps.executeQuery();
			int[] levelCount = new int[7];

			while (rs.next()) {
				try {
					System.out.println(Integer.parseInt(rs.getString(2)));
					levelCount[rs.getInt("level") - 1] = rs.getInt(1);
				}catch (NumberFormatException e){
					//Pusta kolekcja, bywa no
				}
			}
			return new FlashCardCollectionData(levelCount[0], levelCount[1], levelCount[2], levelCount[3],
					levelCount[4], levelCount[5], levelCount[6]);
		}
	}

	@Override
	public ObservableList<FlashCardCollection> getAllCollections() throws SQLException {
		String sql = "SELECT * FROM collections;";
		try (Connection c = getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<FlashCardCollection> result = new ArrayList<>(rs.getFetchSize());
			while (rs.next()) {
				Language first = getLanguageById(rs.getInt("first_language"));
				Language second =getLanguageById(rs.getInt("second_language"));
				int id = rs.getInt("id");
				String name = rs.getString("name");
				FlashCardCollection fcc = new FlashCardCollection(id, name, first, second);
				result.add(fcc);
			}
			return FXCollections.observableArrayList(result);
		} catch (NoSuchLanguageException e) {
			System.err.println("Błąd podczas pobierania fiszek z kloekcji, niepoprawne id jezyka: "+e.getMessage());
			return FXCollections.observableArrayList(new ArrayList<>(0));
		}
	}

	@Override
	public boolean addFlashCard(FlashCardCollection collection, String first, String second) throws SQLException {
		if(first == null || first.length() < 1 || second == null || second.length() < 1){
			throw new IllegalArgumentException();
		}
		String sql = "INSERT INTO flashcards (first, second, level, collection) VALUES (?, ?, ?, ?);";
		try (Connection c = getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, first);
			ps.setString(2, second);
			ps.setInt(3, 1);
			ps.setInt(4, collection.getId());
			return ps.executeUpdate() > 0;
		}
	}

	/**
	 * Usuwa fiszkę z bazy danych
	 *
	 * @param flashCard fiszka do usunięcia
	 * @return true jeżeli fiszka została usunięta
	 */
	@Override
	public boolean remove(FlashCard flashCard) throws SQLException {
		String sql = "DELETE FROM flashcards WHERE id = ?;";
		try (Connection c = getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, flashCard.getId());
			return ps.executeUpdate() > 0;
		}

	}

	@Override
	public void update(FlashCard flashCard) throws SQLException {
		String sql = "UPDATE flashcards SET first = ?, second = ?, level = ? WHERE id = ?;";
		try (Connection c = getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, flashCard.getFirst());
			ps.setString(2, flashCard.getSecond());
			ps.setInt(3, flashCard.getLevel());
			ps.setInt(4, flashCard.getId());
			ps.execute();
		}
	}

	/**
	 * Aktualizuje dane o wszystkich fiszkach w liście
	 *
	 * @param flashCards lista fiszek do aktualizowania
	 */
	@Override
	public void updateFlashCards(ObservableList<FlashCard> flashCards) {//todo

	}

	@Override
	public ObservableList<FlashCard> getFlashCards(FlashCardCollection collection) throws SQLException {
		String sql = " SELECT id, first, second, level FROM flashcards WHERE collection = ?;";
		try (Connection c = getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, collection.getId());
			ResultSet rs = ps.executeQuery();
			ArrayList<FlashCard> result = new ArrayList<>(rs.getFetchSize());
			while (rs.next()) {
				int id = rs.getInt("id");
				String first = rs.getString("first");
				String second = rs.getString("second");
				int level = rs.getInt("level");
				FlashCard flashCard = new FlashCard(id, first, second, level);
				result.add(flashCard);
			}
			return FXCollections.observableArrayList(result);
		}
	}

	private int getLanguageId(Language language) throws NoSuchLanguageException, SQLException {
		try (Connection c = getConnection()) {
			String sql = "SELECT id FROM languages WHERE name = ?;";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, language.name());
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				return result.getInt("id");
			} else {
				throw new NoSuchLanguageException(language);
			}
		}
	}

	private void createLanguagesTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS languages ( " +
				" id INTEGER PRIMARY KEY, " +
				" name TEXT NOT NULL UNIQUE " +
				");";
		try (Connection c = getConnection()) {
			c.prepareStatement(sql).execute();
		}
	}

	private void createCollectionsTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS collections (" +
				" id INTEGER PRIMARY KEY, " +
				" name TEXT NOT NULL, " +
				" first_language INTEGER REFERENCES languages(id), " +
				" second_language INTEGER REFERENCES languages(id) " +
				");";
		try (Connection c = getConnection()) {
			c.prepareStatement(sql).execute();
		}
	}

	private Language getLanguageById(int id) throws SQLException, NoSuchLanguageException {
		String sql = "SELECT name FROM languages WHERE id = ?;";
		try (Connection c = getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return Language.valueOf(rs.getString("name"));
			} else {
				throw new NoSuchLanguageException("No language with id of " + id);
			}
		}
	}

	private void createFlashcardsTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS flashcards ( " +
				" id INTEGER PRIMARY KEY, " +
				" level INTEGER CHECK(level >= 1 AND level <= 7) DEFAULT 1, " +
				" first TEXT NOT NULL, " +
				" second TEXT NOT NULL, " +
				" collection INTEGER REFERENCES collections(id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
				");";
		try (Connection c = getConnection()) {
			c.prepareStatement(sql).execute();
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(connectionString);
	}
}
