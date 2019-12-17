Narrative

In order to add new flash card
AS a user
I want to add new flash card to data base

Scenario: User adds flash card to data base

Given data base
When user creates a flashcard <name> with <translation> 
When user adds flashcard
Then amount of flashcards should increase

Examples:
|name|translation|
|name1|translation1|
|name2|translation2|
|name3|translation3|
|name4|translation4|
|name5|translation5|
|name6|translation6|