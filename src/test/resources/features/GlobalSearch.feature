@globalsearch
Feature: Global Search functionality

  @1
  Scenario: Verify User can enter the search keyword and click Enter button to get the Search results
    Given User opens URL "https://www.bestbuy.com/"
    When User types "airpods" in Search
    And User presses Enter button
    Then Search results pop up

  @2
  Scenario Outline: Verify every entry name contains <keyword> keyword
    Given User opens URL "<url>"
    When User types "<keyword>" in Search
    And User clicks Search button
    Then Search result entries should contain "<keyword>"

    Examples:
      | url                      | keyword |
      | https://www.bestbuy.com/ | airpod  |
      | https://www.bestbuy.com/ | toaster |
      | https://www.bestbuy.com/ | washer  |

  @3
  Scenario: Verify clicking on Reset button in Search deletes the typed entry
    Given User opens URL "https://www.bestbuy.com/"
    When User types "airpods" in Search
    Then User clicks Reset button
    And Verify search field is empty

  @4
  Scenario: Verify User can start typing the word in the text box, and all suggestions should contain the typed word
    Given User opens URL "https://www.bestbuy.com/"
    When User types "air" in Search
    Then Verify all suggestions should contain "air"

  @5
  Scenario: Verify search history presents most recent Search requests
    Given User opens URL "https://www.bestbuy.com/"
    And User searches by keywords
      | iron    |
#    | washer  |
    When User clicks on Global Search field
    Then Previous history search results pop up
      | iron    |
#    | washer  |






