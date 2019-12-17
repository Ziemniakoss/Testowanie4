Narrative

In order to delete flash card
AS a user
I want to delete flash card from data base

Scenario: User deletes flash card from data base

Given data base
And flash card <name> with <translation> 
When user deletes flashcard
Then amount of collection should decrease


Examples:
|name|translation|
|name1|translation1|
|name2|translation2|
|name3|translation3|
|name4|translation4|
|name5|translation5|
|name6|translation6|