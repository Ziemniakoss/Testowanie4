package testprojcztery.database;


public class FlashCard {
	private final int id;
	private int level;
	private String first;
	private String second;


	FlashCard(int id, String first, String second, int level) {
		this.id = id;
		this.first = first;
		this.second = second;
		this.level = level;
	}


	public int getId() {
		return id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public int getLevel() {
		return level;
	}

	public void resetLevel(){
		level = 1;
	}

	public void increaseLevel(){
		if(level != 7){
			level++;
		}
	}
}
