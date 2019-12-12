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

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FlashCardCollection that = (FlashCardCollection) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return id;
	}
}
