package pages;

import elements.Button;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage{

    @FindBy(css = ".product__buy button")
    private Button buyButton;

    @FindBy(xpath = "//h1[@class='product__title']")
    private WebElement productTitle;

    public ProductDetailsPage() {
        super();
    }

    public void clickBuyButton(){
        moveToProductTitle();
        buyButton.safeClick();
    }

    public void moveToProductTitle(){
        waitUntilVisibilityOfElement(productTitle);
        Actions actions = new Actions(driver);
        actions.moveToElement(productTitle).perform();
    }
}
