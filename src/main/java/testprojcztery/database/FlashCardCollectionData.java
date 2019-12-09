package testprojcztery.database;

public class FlashCardCollectionData {
	private final int id;
	private final int cardsOnLevelOne;
	private final int cardsOnLevelTwo;
	private final int cardsOnLevelThree;
	private final int cardsOnLevelFour;
	private final int cardsOnLevelFive;
	private final int cardsOnLevelSix;
	private final int cardsOnLevelSeven;

	public FlashCardCollectionData(int id, int cardsOnLevelOne, int cardsOnLevelTwo,
								   int cardsOnLevelThree, int cardsOnLevelFour, int cardsOnLevelFive,
								   int cardsOnLevelSix, int cardsOnLevelSeven) {
		this.id = id;
		this.cardsOnLevelOne = cardsOnLevelOne;
		this.cardsOnLevelTwo = cardsOnLevelTwo;
		this.cardsOnLevelThree = cardsOnLevelThree;
		this.cardsOnLevelFour = cardsOnLevelFour;
		this.cardsOnLevelFive = cardsOnLevelFive;
		this.cardsOnLevelSix = cardsOnLevelSix;
		this.cardsOnLevelSeven = cardsOnLevelSeven;
	}

	public int getCardsOnLevelOne() {
		return cardsOnLevelOne;
	}

	public int getCardsOnLevelTwo() {
		return cardsOnLevelTwo;
	}

	public int getCardsOnLevelThree() {
		return cardsOnLevelThree;
	}

	public int getCardsOnLevelFour() {
		return cardsOnLevelFour;
	}

	public int getCardsOnLevelFive() {
		return cardsOnLevelFive;
	}

	public int getCardsOnLevelSix() {
		return cardsOnLevelSix;
	}

	public int getCardsOnLevelSeven() {
		return cardsOnLevelSeven;
	}
}
