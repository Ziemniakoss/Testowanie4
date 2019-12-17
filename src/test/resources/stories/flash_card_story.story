Narrative:
In order to show flashcard functionality
As a user
I want to create fishcards and guess translations

Scenario: User adding a new flashcard

Given a word <name> , <translation> and <level>
When user creates a flashcard
Then the new flashcard should have <name> , <translation> and <level>


Scenario: User changing translation

Given a flashcard with <name> and <translation> on <level>
When user changes translation for <newtranslation>
Then the new translation should be <newtranslation> and the flashcard should be on the same <level>


Scenario: User guessing answers

Given a flashcard with <name> and <translation> on <level>
When user <guessed> answer
Then the new level should be <newlevel>


Examples:
|name|translation|newtranslation|level|guessed|newlevel|
|name1|translation1|newtranslation1|1|true|2|
|name2|translation2|newtranslation2|3|true|4|
|name3|translation3|newtranslation3|7|true|7|
|name4|translation4|newtranslation4|1|false|1|
|name5|translation5|newtranslation5|3|false|1|
|name6|translation6|newtranslation6|7|false|1|
