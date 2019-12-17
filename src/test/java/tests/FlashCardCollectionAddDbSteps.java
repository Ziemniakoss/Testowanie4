package tests;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import testprojcztery.Language;
import testprojcztery.database.FlashCardCollection;
import testprojcztery.database.NoSuchLanguageException;
import testprojcztery.database.SqliteFlashcardsDatabase;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FlashCardCollectionAddDbSteps {
    SqliteFlashcardsDatabase sqliteFlashcardsDatabase;
    FlashCardCollection flashCardCollection;
    int amountBefore;

    @Given("data base")
    public void givenDataBase() throws SQLException {
        sqliteFlashcardsDatabase = SqliteFlashcardsDatabase.getInstance();
        amountBefore = sqliteFlashcardsDatabase.getAllCollections().size();
    }

    @When("user creates a collection <name>, <language> and <translationlanguage>")
    public void whenUserCreatesACollectionnamelanguageAndtranslationlanguage(@Named("name") String name, @Named("language") String language, @Named("translationlanguage") String translationlanguage) {
        flashCardCollection = new FlashCardCollection(0, name, Language.valueOf(language), Language.valueOf(translationlanguage));
    }

    @When("user adds collection")
    public void whenUserAddsCollection() throws NoSuchLanguageException, SQLException {
        sqliteFlashcardsDatabase.createCollection(flashCardCollection.getName(), flashCardCollection.getFirstLanguage(), flashCardCollection.getSecondLanguage());
    }

    @Then("amount of collection should increase")
    public void thenAmountOfCollectionShouldIncrease() throws SQLException {
        int amountAfter = sqliteFlashcardsDatabase.getAllCollections().size();
        assertEquals(amountBefore + 1, amountAfter);
    }
}
