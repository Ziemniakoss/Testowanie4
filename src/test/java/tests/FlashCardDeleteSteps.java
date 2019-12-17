package tests;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import testprojcztery.database.FlashCard;
import testprojcztery.database.FlashCardCollection;
import testprojcztery.database.SqliteFlashcardsDatabase;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class FlashCardDeleteSteps {
    SqliteFlashcardsDatabase sqliteFlashcardsDatabase;
    FlashCardCollection collection;
    FlashCard flashCard;
    int amountBefore;

    @Given("data base")
    public void givenDataBase() throws SQLException {
        sqliteFlashcardsDatabase = SqliteFlashcardsDatabase.getInstance();
        collection = sqliteFlashcardsDatabase.getAllCollections().get(0);
    }

    @Given("flash card <name> with <translation>")
    public void givenFlashCardnameWithtranslation(@Named("name") String name, @Named("translation") String translation) throws SQLException {
        flashCard = new FlashCard(0, name, translation, 1);
        sqliteFlashcardsDatabase.addFlashCard(collection, flashCard.getFirst(), flashCard.getSecond());
        amountBefore = sqliteFlashcardsDatabase.getFlashCards(collection).size();
    }

    @When("user deletes flashcard")
    public void whenUserDeletesFlashcard() throws SQLException {
        sqliteFlashcardsDatabase.remove(sqliteFlashcardsDatabase.getFlashCards(collection).get(amountBefore - 1));
    }

    @Then("amount of collection should decrease")
    public void thenAmountOfCollectionShouldDecrease() throws SQLException {
        assertEquals(amountBefore - 1, sqliteFlashcardsDatabase.getFlashCards(collection).size());
    }
}
