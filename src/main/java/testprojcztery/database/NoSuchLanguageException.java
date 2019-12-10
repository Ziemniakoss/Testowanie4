package testprojcztery.database;

import testprojcztery.Language;

public class NoSuchLanguageException extends Exception {
	private final Language language;
	public NoSuchLanguageException(Language language) {
		super("language not in database: "+ language);
		this.language = language;
	}

	public Language getLanguage() {
		return language;
	}
}
