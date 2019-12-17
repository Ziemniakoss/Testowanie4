package tests;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import testprojcztery.Language;
import testprojcztery.database.FlashCardCollection;

import static org.junit.Assert.assertTrue;

public class FlashCardCollectionSteps {
    String collectionName;
    Language language;
    Language translationLanguage;
    FlashCardCollection flashCardCollection;

    @Given("a collection <name>, <language> and <translationlanguage>")
    public void givenACollectionnamelanguageAndtranslationlanguage(@Named("name") String name, @Named("language") String language, @Named("translationlanguage") String translationlanguage) {
        this.collectionName = name;
        this.language = Language.valueOf(language);
        this.translationLanguage = Language.valueOf(translationlanguage);
    }

    @When("user creates a new flashcard collection")
    public void whenUserCreatesANewFlashcardCollection() {
        flashCardCollection = new FlashCardCollection(0, collectionName, language, translationLanguage);
    }

    @Then("the new flashcard collection should have <name> , <language> and <translationlanguage>")
    public void thenTheNewFlashcardCollectionShouldHavenamelanguageAndtranslationlanguage(@Named("name") String name, @Named("language") String language, @Named("translationlanguage") String translationlanguage) {
        System.out.println(flashCardCollection.getFirstLanguage().toString());
        System.out.println(language);
        assertTrue(flashCardCollection.getName() == name);
        assertTrue(flashCardCollection.getFirstLanguage().name().compareTo(language) == 0);
        assertTrue(flashCardCollection.getSecondLanguage().name().compareTo(translationlanguage) == 0 );
    }



}
