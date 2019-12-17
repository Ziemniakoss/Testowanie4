Narrative:

In order to group flashcards
As a user 
I want to create flashcard collection


Scenario: User adds a new flashcard collection

Given a collection <name>, <language> and <translationlanguage>
When user creates a new flashcard collection
Then the new flashcard collection should have <name> , <language> and <translationlanguage>

Examples:
|name|language|translationlanguage|
|name1|Niemiecki|Polski|
|name2|Polski|Niemiecki|
|name3|Angielski|Polski|