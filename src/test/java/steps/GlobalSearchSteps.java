package steps;

import Utilities.BrowserUtils;
import Utilities.Driver;
import Utilities.TestConstant;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.Homepage;

import java.util.List;

public class GlobalSearchSteps {

    WebDriver driver = Driver.getDriver();
    Homepage homepage = new Homepage();

    @Given("User opens URL {string}")
    public void user_opens_URL(String url) {
       driver.get(url);
       if(BrowserUtils.doesWebElementExist(homepage.CLOSE_BUTTON)) {
           homepage.popUpCloseButton.click();
       }
    }

    @When("User types {string} in Search")
    public void user_types_in_Search(String keyword) {
        homepage.searchField.click();
        homepage.searchField.sendKeys(keyword);
    }

    @When("User presses Enter button")
    public void user_presses_Enter_button() {
        homepage.searchField.sendKeys(Keys.ENTER);
    }

    @Then("Search results pop up")
    public void search_results_pop_up() {
        homepage.verifySearchResultCount();
    }

    @When("User clicks Search button")
    public void user_clicks_Search_button() {
        BrowserUtils.clickWebElement(homepage.searchButton, TestConstant.GENERAL_WAIT);
    }

    @Then("Search result entries should contain {string}")
    public void search_result_entries_should_contain(String keyword) {
        boolean containsKeyword = homepage.searchResults.stream().allMatch(e -> e.getText().toLowerCase().contains(keyword.toLowerCase()));
        Assert.assertTrue("Doesn't contain keyword", containsKeyword);
    }

    @Given("User searches by keywords")
    public void user_searches_by_keywords(List<String> keywords) throws InterruptedException {
        homepage.searchMultipleTimes(keywords);
    }

    @Given("User navigates to homepage")
    public void user_navigates_to_homepage() {
       homepage.homepageButton.click();
    }

    @When("User clicks on Global Search field")
    public void user_clicks_on_Global_Search_field() {
        Actions actions = new Actions(driver);
        actions.moveToElement(homepage.searchField).doubleClick().build().perform();
    }

    @Then("Previous history search results pop up")
    public void previous_history_search_results_pop_up(List<String> keywords) {
        BrowserUtils.waitForVisibility(homepage.previousSearchHistoryWE, TestConstant.GENERAL_WAIT);
        boolean containsPreviousSearch = homepage.previousSearchHistory.stream().allMatch(e -> keywords.contains(e.getText().toLowerCase()));
        Assert.assertTrue("Doesn't contain previous search", containsPreviousSearch);
    }

    @Then("Verify all suggestions should contain {string}")
    public void verify_all_suggestions_should_contain(String keyword) {
        boolean containsSuggestions = homepage.searchSuggestions.stream().allMatch(e -> e.getAttribute("title").toLowerCase().contains(keyword));
        Assert.assertTrue("Doesn't contain suggestions", containsSuggestions);
    }

    @Then("User clicks Reset button")
    public void user_clicks_Reset_button() {
        homepage.resetButton.click();
    }

    @Then("Verify search field is empty")
    public void verify_search_field_is_empty() {
        String searchFieldValue = homepage.searchField.getAttribute("value");
        Assert.assertTrue("Search field is not empty", searchFieldValue == null || searchFieldValue.isEmpty());
    }


}
