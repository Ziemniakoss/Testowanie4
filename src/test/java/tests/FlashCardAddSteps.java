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


public class FlashCardAddSteps  {
    SqliteFlashcardsDatabase sqliteFlashcardsDatabase;
    FlashCardCollection collection;
    FlashCard flashCard;
    int amountBefore;


    @Given("data base")
    public void givenDataBase() throws SQLException {
        sqliteFlashcardsDatabase = SqliteFlashcardsDatabase.getInstance();
        collection = sqliteFlashcardsDatabase.getAllCollections().get(0);
        amountBefore = sqliteFlashcardsDatabase.getFlashCards(collection).size();
    }

    @When("user creates a flashcard <name> with <translation>")
    public void whenUserCreatesAFlashcardnameWithtranslation(@Named("name") String name, @Named("translation") String translation) {
        flashCard = new FlashCard(0,name, translation, 1);
    }

    @When("user adds flashcard")
    public void whenUserAddsFlashcard() throws SQLException {
        sqliteFlashcardsDatabase.addFlashCard(collection, flashCard.getFirst(), flashCard.getSecond());
    }

    @Then("amount of flashcards should increase")
    public void thenAmountOfFlashcardsShouldIncrease() throws SQLException {
        assertEquals(amountBefore+1,sqliteFlashcardsDatabase.getFlashCards(collection).size());
    }
}
