package testprojcztery.database;

public class FlashCardCollectionData {
	private final int cardsOnLevelOne;
	private final int cardsOnLevelTwo;
	private final int cardsOnLevelThree;
	private final int cardsOnLevelFour;
	private final int cardsOnLevelFive;
	private final int cardsOnLevelSix;
	private final int cardsOnLevelSeven;
	private final int totalCards;

	public FlashCardCollectionData(int cardsOnLevelOne, int cardsOnLevelTwo,
								   int cardsOnLevelThree, int cardsOnLevelFour, int cardsOnLevelFive,
								   int cardsOnLevelSix, int cardsOnLevelSeven) {
		this.cardsOnLevelOne = cardsOnLevelOne;
		this.cardsOnLevelTwo = cardsOnLevelTwo;
		this.cardsOnLevelThree = cardsOnLevelThree;
		this.cardsOnLevelFour = cardsOnLevelFour;
		this.cardsOnLevelFive = cardsOnLevelFive;
		this.cardsOnLevelSix = cardsOnLevelSix;
		this.cardsOnLevelSeven = cardsOnLevelSeven;
		totalCards = cardsOnLevelOne + cardsOnLevelTwo + cardsOnLevelThree + cardsOnLevelFour +
				cardsOnLevelFive + cardsOnLevelSix + cardsOnLevelSeven;
	}

	public int getTotalCards() {
		return totalCards;
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

	@Override
	public String toString() {
		return "FlashCardCollectionData{" +
				"cardsOnLevelOne=" + cardsOnLevelOne +
				", cardsOnLevelTwo=" + cardsOnLevelTwo +
				", cardsOnLevelThree=" + cardsOnLevelThree +
				", cardsOnLevelFour=" + cardsOnLevelFour +
				", cardsOnLevelFive=" + cardsOnLevelFive +
				", cardsOnLevelSix=" + cardsOnLevelSix +
				", cardsOnLevelSeven=" + cardsOnLevelSeven +
				", totalCards=" + totalCards +
				'}';
	}
}
