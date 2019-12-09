package testprojcztery.database;

import testprojcztery.Language;

public class FlashCardCollection {
	private final int id;
	private String name;
	private final Language firstLanguage;
	private final Language secondLanguage;

	FlashCardCollection(int id, String name, Language firstLanguage, Language secondLanguage) {
		this.id = id;
		this.name = name;
		this.firstLanguage = firstLanguage;
		this.secondLanguage = secondLanguage;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Language getFirstLanguage() {
		return firstLanguage;
	}

	public Language getSecondLanguage() {
		return secondLanguage;
	}
}
