package pages;

import Utilities.BrowserUtils;
import Utilities.Driver;
import Utilities.TestConstant;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Homepage {
    WebDriver driver;

    public Homepage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    public final String CLOSE_BUTTON = "//button[@class='c-close-icon c-modal-close-icon']";
    public final String RESET_BUTTON = "header-clear-search-icon";

    @FindBy(id = "gh-search-input")
    public WebElement searchField;

    @FindBy(xpath = "//button[@class='header-search-button']")
    public WebElement searchButton;

    @FindBy(xpath = "//a[@title='BestBuy.com']")
    public WebElement homepageButton;

    @FindBy(id = RESET_BUTTON)
    public WebElement resetButton;

    @FindBy(xpath = "//div[@id='main-results']//div[@class='sku-title']")
    public List<WebElement> searchResults;

    @FindBy(xpath = "//span[@class='HumanBBYDigital-Medium']")
    public List<WebElement> previousSearchHistory;

    @FindBy(xpath = "//span[@class='HumanBBYDigital-Medium']")
    public WebElement previousSearchHistoryWE;

    @FindBy(xpath = "//span[@class='item-count']")
    public WebElement itemsCount;

    @FindBy(xpath = "//a[@class='suggest-target suggest-wk-press-fix']")
    public List<WebElement> searchSuggestions;

    @FindBy(xpath = CLOSE_BUTTON)
    public WebElement popUpCloseButton;

    public void verifySearchResultCount() {
        BrowserUtils.waitForVisibility(itemsCount, TestConstant.GENERAL_WAIT);
        int resultsCount = Integer.parseInt(itemsCount.getText().split(" ")[0]);
        Assert.assertTrue("No search result", resultsCount > 0);
    }

    public void searchMultipleTimes(List<String> keywords) throws InterruptedException {
        for(int i = 0; i < keywords.size(); i++) {
            searchField.click();
            searchField.sendKeys(Keys.COMMAND + "a");
            searchField.sendKeys(Keys.DELETE);
            searchField.sendKeys(keywords.get(i));
            searchField.sendKeys(Keys.ENTER);
            BrowserUtils.waitForVisibility(itemsCount, TestConstant.GENERAL_WAIT);
            homepageButton.click();
        }
    }

}
