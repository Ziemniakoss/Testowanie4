package tests;

import org.jbehave.core.annotations.*;
import testprojcztery.database.FlashCard;

import static org.junit.Assert.assertTrue;

public class FlashCardSteps {
    private String name;
    private String translation;
    private int level;
    private FlashCard flashCard;

    @Given("a word <name> , <translation> and <level>")
    public void givenAWordnametranslationAndlevel(@Named("name") String name, @Named("translation") String translation, @Named("level") int level) {
        this.name = name;
        this.translation = translation;
        this.level = level;
    }

    @When("user creates a flashcard")
    public void whenUserCreatesAFlashcard() {
        flashCard = new FlashCard(0, name, translation, level);
    }

    @Then("the new flashcard should have <name> , <translation> and <level>")
    public void thenTheNewFlashcardShouldHavenametranslationAndlevel(@Named("name") String name, @Named("translation") String translation) {
        assertTrue(flashCard.getFirst() == name && flashCard.getSecond() == translation && flashCard.getLevel() == level);
    }

    @Given("a flashcard with <name> and <translation> on <level>")
    public void givenAFlashcardWithnameAndtranslationOnlevel(@Named("name") String name, @Named("translation") String translation, @Named("level") int level) {
        this.name = name;
        this.translation = translation;
        this.level = level;
        flashCard = new FlashCard(0, name, translation, level);
    }

    @When("user changes translation for <newtranslation>")
    public void whenUserChangesTranslationFornewtranslation(@Named("translation") String translation) {
        flashCard.setSecond(translation);
    }

    @Then("the new translation should be <newtranslation> and the flashcard should be on the same <level>")
    public void thenTheNewTranslationShouldBenewtranslationAndTheFlashcardShouldBeOnTheSamelevel(@Named("newtranslation") String newTranslation, @Named("level") int level) {
        assertTrue(flashCard.getSecond() == newTranslation && flashCard.getLevel() == level);
    }

    @When("user <guessed> answer")
    public void whenUserguessedAnswer(@Named("guessed") boolean guessed) {
        if (guessed){
            flashCard.increaseLevel();
        } else {
            flashCard.resetLevel();
        }
    }

    @Then("the new level should be <newlevel>")
    public void thenTheNewLevelShouldBenewlevel(@Named("newlevel") int newlevel) {
        assertTrue(flashCard.getLevel() == newlevel);
    }


}
