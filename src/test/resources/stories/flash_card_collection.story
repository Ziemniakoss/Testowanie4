Scenario: User adds a new flashcard collection

Given a collection <name>, <language> and <translationlanguage>
When user creates a new flashcard collection
Then the new flashcard collection should have <name> , <language> and <translationlanguage>

Examples:
|name  |language |translationlanguage |
|name1 |GERMAN   |POLISH              |
|name2 |POLISH   |GERMAN              |
|name3 |ENGLISH  |POLISH              |
