package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//div[@data-filter-name='producer']//input[@name='searchline']")
    private WebElement brandFilterSearchInput;

    @FindBy(xpath = "//div[@data-filter-name='producer']//ul//a[@class='checkbox-filter__link']")
    private List<WebElement> brandFilterLinksList;

    @FindBy(css = ".catalog-settings__sorting > select")
    private WebElement sortDropdown;

    @FindBy(xpath = "//div[@data-filter-name='sell_status']//a")
    private List<WebElement> sellStatusFilterLinksList;

    @FindBy(css = "ul.catalog-grid > li:first-child a.goods-tile__heading")
    private WebElement firstProductTileHeadingLink;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void enterBrandNameInSearchInput(final String brandName){
        Actions actions = new Actions(driver);
        waitUntilVisibilityOfElement(brandFilterSearchInput);
        actions.moveToElement(brandFilterSearchInput).perform();
        brandFilterSearchInput.sendKeys(brandName);
    }

    public void clickOnBrandFilterLink(final String brandName) {
        Actions actions = new Actions(driver);
        waitUntilVisibilityOfAllElements(brandFilterLinksList);
        for (var brandFilterLink : brandFilterLinksList){
            if (brandFilterLink.findElement(By.xpath("./child::input"))
                    .getAttribute("id").equals(brandName)){
                actions.moveToElement(brandFilterLink).perform();
                waitUntilElementIsClickable(brandFilterLink);
                brandFilterLink.click();
                break;
            }
        }
    }

    public void sortProductsByOption(final String optionValue){
        Select sortDropdownSelect = new Select(sortDropdown);
        waitUntilVisibilityOfElement(sortDropdown);
        sortDropdownSelect.selectByValue(optionValue);
    }

    public void showOnlyAvailableProducts(){
        Actions actions = new Actions(driver);
        waitUntilVisibilityOfAllElements(sellStatusFilterLinksList);
        for (var sellStatusFilterLink : sellStatusFilterLinksList){
            waitUntilVisibilityOfElement(sellStatusFilterLink);
            if (sellStatusFilterLink.getAttribute("href").contains("sell_status=available")){
                actions.moveToElement(sellStatusFilterLink).perform();
                sellStatusFilterLink.click();
                break;
            }
        }
    }

    public void clickOnFirstProduct(){
        waitUntilVisibilityOfElement(firstProductTileHeadingLink);
        Actions actions = new Actions(driver);
        actions.moveToElement(firstProductTileHeadingLink).perform();
        waitUntilElementIsClickable(firstProductTileHeadingLink);
        firstProductTileHeadingLink.click();
    }
}
