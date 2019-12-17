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

        if(language.compareTo("Polski") == 0){
            this.language = Language.POLISH;
        } else if(language.compareTo("Angielski") == 0){
            this.language = Language.ENGLISH;
        } else {
            this.language = Language.GERMAN;
        }

        if(translationlanguage.compareTo("Polski") == 0){
            this.translationLanguage = Language.POLISH;
        } else if(translationlanguage.compareTo("Angielski") == 0){
            this.translationLanguage = Language.ENGLISH;
        } else {
            this.translationLanguage = Language.GERMAN;
        }
    }

    @When("user creates a new flashcard collection")
    public void whenUserCreatesANewFlashcardCollection() {
        flashCardCollection = new FlashCardCollection(0, collectionName, language, translationLanguage);
    }

    @Then("the new flashcard collection should have <name> , <language> and <translationlanguage>")
    public void thenTheNewFlashcardCollectionShouldHavenamelanguageAndtranslationlanguage(@Named("name") String name, @Named("language") String language, @Named("translationlanguage") String translationlanguage) {
        System.out.println(flashCardCollection.getFirstLanguage().toString());
        System.out.println(language);
        assertTrue(flashCardCollection.getName() == name && flashCardCollection.getFirstLanguage().toString().compareTo(language) == 0 && flashCardCollection.getSecondLanguage().toString().compareTo(translationlanguage) == 0 );
    }



}
