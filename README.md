# Assignment

How to trigger execution from Jenkins with an automation report - mvn clean verify -Dcucumber.options=“—tags @usertag”

Test scenarios:
1. Verify User can enter the search keyword and click Enter button to get the Search results
2. Verify every entry name contains keyword
3. Verify clicking on Reset button in Search deletes the typed entry
4. Verify User can start typing the word in the text box, and all suggestions should contain the typed word 
5. Verify search history presents most recent Search requests

With regards to the pertinent negative scenarios, I'd outline a few like these:
1. Random User input in the search field (special characters), and ensuring 1) we are being redirected to a different page 2) capturing and verifying we're getting a message like 'Hmmm, we didn't find anything for "User input"'
2. Random User input in the search field (input like '0123456789'), and ensuring 1) we are being redirected to a different page 2) capturing and verifying we're getting a message like 'Hmmm, we didn't find anything for "User input"'

In essence, there's not that many negative scenarios w.r.to the Search line that are reusable and useful.

With regards to API testing, most typically I'd be using Get requests in order to compare the info found in the database/UI and in the API response.
