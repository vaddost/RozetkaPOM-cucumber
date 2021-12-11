package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(name = "search")
    private WebElement searchInput;

    @FindBy(xpath = "//button[contains(@class, 'search-form__submit')]")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void enterSearchPhrase(final String searchPhrase){
        waitUntilVisibilityOfElement(searchInput);
        searchInput.sendKeys(searchPhrase);
    }

    public void clickSearchButton(){
        waitUntilVisibilityOfElement(searchButton);
        searchButton.click();
    }
}
