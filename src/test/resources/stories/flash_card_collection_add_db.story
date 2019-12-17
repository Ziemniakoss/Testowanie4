Narrative

In order to add new collection
AS a user
I want to add new collection to data base

Scenario: User adds collection to data base

Given data base
When user creates a collection <name>, <language> and <translationlanguage>
When user adds collection
Then amount of collection should increase


Examples:
|name|language|translationlanguage|
|name1|GERMAN|POLISH|
|name2|POLISH|GERMAN|
|name3|ENGLISH|POLISH|