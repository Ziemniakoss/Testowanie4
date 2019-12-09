package testprojcztery;

public enum Language {
	POLISH,
	ENGLISH,
	GERMAN;

	@Override
	public String toString() {
		switch (this){
			case GERMAN:
				return "Niemiecki";
			case POLISH:
				return "Polski";
			case ENGLISH:
				return "Angielski";
			default:
				return super.toString();
		}
	}
}
