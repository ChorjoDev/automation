# Computer Database Test Framework

I have written the framework using the Page Object Model and Java 
as the language. Normally I would implement the Page factory, but
it seemed a little overkill for this small project.

The tests were written using Cucumber feature files with the logic 
for the tests residing in the step definitions. Reason for the use 
of Cucumber, it is widely used and has been adapted in most organisations.

Java 8 was used to run and develop.

## Test Data 
I used a library called Faker to generate random test data for the 
creation of computers. This library is really useful for generating 
random test data which gives a little more versatility. Normally 
with a larger scale project with different objects of data required
then I would normally introduce factories to generate and customise 
data for testing. Similar to what I use in Ruby there is a library 
which helps with this creation FactoryBot which works well with Faker.

FAKER:          https://github.com/DiUS/java-faker

FACTORY BOT:    https://github.com/topicusoverheid/java-factory-bot 

## Run the Tests

I have only concentrated my time on the tests and automating them. I 
would normally do cross browser and Dcokerise the tests to run in CICD.
But as this was not a requirement then I haven't proceeded to do so.
However, I can do the following if needed to show. 

I have used a simple JUnit test runner and within IntelliJ I have just
used the run buttons from within the class to run them. I have left an 
example of when you might want to run a specific tag but it is commented
out. 

Running Jmeter jmx script, you can just open it in Jmeter and run it from 
there.

## CRUD Tests 
My approach before writing the automation tests is to complete some 
exploratory testing to get a feel for the app, locate some obvious 
defects and work out functionality. 

This allowed me to write the tests via notes and then look to create the
page objects that I need for the tests. 

Prior to writing the automation tests I used Jmeter to replicate the
requests when creating a record. The purpose for this was to check that
the validation was not only covered by the frontend. I have included 
this JMX file. 

The create tests I noticed a few issues around the behaviour that would
in a real scenario need to be questioned to confirm the correct requirements.
The name field accepted special characters, this is something to check on.
Things like filtering might be a problem if the filter field excluded them
from entry, or they are removed when added to the database.

The name field was the only mandatory field, so the obvious test would be
to attempt to create a computer without a name but has other fields filled 
in or not filled in. This brought up a red warning around the field to 
highlight it was a required field.

The name field was also not restricted by the amount of characters that 
could be entered. Further analysis needed on this but by entering 15k 
characters brought up a Heroku error page. Not good for security purposes 
as the error isn't handled. Maybe the database has a character limit which 
isn't matched on the frontend.

The creation of a computer allowed duplicate entries which didn't feel 
correct so something I would question. They do appear as separate records,
so it seems they are indexed in the database but not great for filtering 
as you couldn't determine the difference with the current data captured.

This same red warning alert appeared around the date fields when the date 
was not the correct format. Further validation checks on the date field 
brought up issues around the format of the year. It allowed more than 4 
digits for the year which then resulted in problems when displaying the 
added or updated computer on the homepage. It also allowed invalid or 
years that would not be allowed considering when computers were first 
around.

Alpha characters were attempted in the date fields yielded the correct 
result and passed validation. The dates didn't have validation around 
introduced and discontinued. Introduced could be before discontinued.

When mapping out the elements for the manufacturer, I noticed the dropdown
had values as numbers. This was fine, but it seemed to miss a number which
could trip over automation tests or developers when out of order.

Cancel and Back button functionality worked as expected.The homepage table 
sorting tabs didn't work, nothing changed when these were selected.

Filtering was used to check the record was present after creation, 
updating and deletion. The filtering seemed to work well, but the 
issue with duplicate records being allowed would cause problems and
from assumption I have assumed this should not be allowed. 

Deletion of the record I have checked the number is reduced 
after deletion. Checked on a page refresh that the record was in fact 
deleted and not just done in state. 

Update of the record I have picked a random record, update 
fields and confirm updated. Done some individual field updating and 
checking. The dates would have been checked, and some boundary testing 
on start of year, end of year. Months that have 30 or fewer days and 
attempting to add dates that are not possible.

The validation of fields could have been centralised and used for any 
of the transactions as all the fields were the same across the board. 